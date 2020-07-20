package com.xiaoshu.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.DeptMapper;
import com.xiaoshu.dao.EmpMapper;
import com.xiaoshu.entity.Dept;
import com.xiaoshu.entity.Emp;
import com.xiaoshu.entity.EmpVo;
import com.xiaoshu.entity.Log;

@Service
public class EmpService {

	@Autowired
	private EmpMapper empMapper;
	@Autowired
	private DeptMapper deptMapper;

	public PageInfo<EmpVo> findPage(EmpVo empVo, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<EmpVo> list=empMapper.findAll(empVo);
		
		return new PageInfo<>(list);
	}

	public Emp findByName(String tbEmpName) {
		Emp emp = new Emp();
		emp.setTbEmpName(tbEmpName);
		Emp one = empMapper.selectOne(emp);
		return one;
	}

	public void update(Emp emp) {
		empMapper.updateByPrimaryKeySelective(emp);
		
	}

	public void add(Emp emp) {
		empMapper.insert(emp);
		
	}

	public void delete(int parseInt) {
		empMapper.deleteByPrimaryKey(parseInt);
		
	}

	public void importE(MultipartFile importFile) throws InvalidFormatException, IOException {
		Workbook wb = WorkbookFactory.create(importFile.getInputStream());
		Sheet sheet = wb.getSheetAt(0);
		int num = sheet.getLastRowNum();
		for (int i = 0; i <num; i++) {
			Row row = sheet.getRow(i+1);
			String name = row.getCell(0).toString();
			String sex = row.getCell(1).toString();
			int age = (int) row.getCell(2).getNumericCellValue();
			String img = row.getCell(3).toString();
			Date birthday = row.getCell(4).getDateCellValue();
			String dname = row.getCell(5).toString();
			/*Cell cell = row.getCell(5);
			String dname = cell.getStringCellValue();*/
			Emp emp = new Emp();
			emp.setTbEmpName(name);
			emp.setTbEmpSex(sex.equals("男")?"1":"0");
			emp.setTbEmpAge(age);
			emp.setTbEmpImg(img);
			emp.setTbEmpBirthday(birthday);
			Dept dept=findD(dname);
			emp.setTbEmpDid(dept.getId());
			add(emp);
			
			
		}
		
		
	}

	private Dept findD(String dname) {
		Dept dept = new Dept();
		dept.setName(dname);
		return deptMapper.selectOne(dept);
		
		
	}

	public List<EmpVo> findE(EmpVo empVo) {
		List<EmpVo> list=empMapper.findAll(empVo);
		return list;
	}
	
}

package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshu.dao.DeptMapper;
import com.xiaoshu.entity.Dept;

@Service
public class DeptService {

	@Autowired
	private DeptMapper deptMapper;

	public List<Dept> findDlist() {
		List<Dept> list = deptMapper.selectAll();
		
		return list;
	}
	
	
}

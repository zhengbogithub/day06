package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Emp;
import com.xiaoshu.entity.EmpExample;
import com.xiaoshu.entity.EmpVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmpMapper extends BaseMapper<Emp> {

	List<EmpVo> findAll(EmpVo empVo);
   }
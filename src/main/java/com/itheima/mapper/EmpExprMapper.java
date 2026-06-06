package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//员工工作经历
@Mapper
public interface EmpExprMapper {

    //批量插入员工工作经历
    void insertBatch(List<EmpExpr> exprList);

    //根据员工ID删除员工工作经历
    void deleteByEmpId(List<Integer> empIds);
}

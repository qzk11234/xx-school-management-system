package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpLogMapper;
import com.itheima.pojo.Operatelog;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Transactional(propagation= Propagation.REQUIRES_NEW)//开启新事务
    @Override
    public void insertLog(Operatelog operatelog) {
        empLogMapper.insert(operatelog);
    }

    @Override
    public PageResult<Operatelog> pageLog(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Operatelog> rows = empLogMapper.pageLog();
        Page<Operatelog> p = (Page<Operatelog>) rows;
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}

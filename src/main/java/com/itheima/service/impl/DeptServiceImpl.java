package com.itheima.service.impl;

import com.itheima.anno.Log;
import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Log
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Log
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Log
    @Override
    public void add(Dept dept) {
        //补全createTime和updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Log
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Log
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

}

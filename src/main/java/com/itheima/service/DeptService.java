package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;



public interface DeptService {

    /**
     * 查询全部部门数据
     */
    List<Dept> findAll();

    /**
     * 删除部门数据
     */
    void deleteById(Integer id);

    /**
     * 新增部门数据
     */
    void add(Dept dept);

    /**
     * 根据id查询部门数据
     */
    Dept getById(Integer id);

    /**
     * 更新部门数据
     */
    void update(Dept dept);
}

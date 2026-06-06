package com.itheima.service;

import com.itheima.pojo.Operatelog;
import com.itheima.pojo.PageResult;

public interface EmpLogService {

    public void insertLog(Operatelog operatelog);

    /**
     * 分页查询日志
     */
    PageResult<Operatelog> pageLog(Integer page, Integer pageSize);

}

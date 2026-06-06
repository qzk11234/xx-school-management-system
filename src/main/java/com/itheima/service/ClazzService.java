package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    /**
     * 分页查询班级信息
     */
    PageResult<Clazz> pageClazz(ClazzQueryParam clazzQueryParam);

    /**
     * 删除班级数据
     */
    void deleteClazz(Integer id);

    /**
     * 添加班级数据
     */
    void addClazz(Clazz clazz);

    /**
     * 根据ID查询班级数据
     */
    Clazz getInfo(Integer id);

    /**
     * 更新班级数据
     */
    void updateClazz(Clazz clazz);

    /**
     * 查询全部班级数据
     */
    List<Clazz> listAll();
}

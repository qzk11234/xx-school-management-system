package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.anno.Log;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;


    /**
     *  分页查询班级信息
     */
    @Log
    @Override
    public PageResult<Clazz> pageClazz(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        PageInfo<Clazz> pageInfo = new PageInfo<>(clazzList);
        List<Clazz> dataList = pageInfo.getList().stream()
                .map(clazz -> {
                    LocalDate now = LocalDate.now();
                    LocalDate begin = clazz.getBeginDate();
                    LocalDate end = clazz.getEndDate();
                    String status;
                    if (now.isBefore(begin)) {
                        status = "未开班";
                    } else if (now.isAfter(end)) {
                        status = "已结课";
                    } else {
                        status = "已开班";
                    }
                    clazz.setStatus(status);
                    return clazz;
                })
                .collect(Collectors.toList());
        return new PageResult<>(pageInfo.getTotal(), dataList);
    }


    @Log
    @Override
    public void deleteClazz(Integer id) {
        clazzMapper.deleteById(id);
    }


    @Log
    @Transactional//事务管理
    @Override
    public void addClazz(Clazz clazz) {
        try {
            clazz.setCreateTime(LocalDateTime.now());
            clazz.setUpdateTime(LocalDateTime.now());
            clazzMapper.insert(clazz);
        } catch (Exception e) {
            throw new RuntimeException("添加班级数据失败", e);
        }
    }

    /**
     * 根据ID查询班级数据
     */
    @Log
    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.selectById(id);
    }

    @Log
    @Override
    public void updateClazz(Clazz clazz) {
        clazzMapper.updateById(clazz);
        clazz.setUpdateTime(LocalDateTime.now());

    }


    /**
     * 查询全部班级数据
     */
    @Log
    @Override
    public List<Clazz> listAll() {
        return clazzMapper.listAll();
    }
}

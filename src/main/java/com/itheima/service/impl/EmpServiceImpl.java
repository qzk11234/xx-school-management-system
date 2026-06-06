package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.anno.Log;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;

import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;


    @Log
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Log
    @Transactional//事务管理
    @Override
    public void save(Emp emp) {
        //保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        //保存员工经历信息
        List<EmpExpr> exprList=emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合，为empid赋值
                exprList.forEach(empExpr->{
                    empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Log
    @Transactional(rollbackFor = Exception.class)//事务管理
    @Override
    public void delete(List<Integer> ids) {
        //删除员工基本信息
        empMapper.deleteBatch(ids);
        //删除员工经历信息
        empExprMapper.deleteByEmpId(ids);
    }

    //根据员工ID查询员工信息以及员工工作经历，然后返回给前端进行展示
    @Log
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 更新员工信息
     */
    @Log
    @Override
    public void update(Emp emp) {
        //根据员工ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //根据员工ID更新员工经历信息
            //删除旧的员工经历信息
            empExprMapper.deleteByEmpId(Arrays.asList(emp.getId()));
            //添加新的员工经历信息
            List<EmpExpr> exprList=emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历集合，为empid赋值
                    exprList.forEach(empExpr->{
                        empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }

    }

    /**
     * 查询全部员工信息
     */
    @Log
    @Override
    public List<Emp> listAll() {
        return empMapper.listAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        //调用mapper接口，根据用户名和密码查询员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        //判断是否存在员工，如果存在，组装登陆成功信息
        if(e !=null){
            log.info("登录成功{}",e);
            //生成jwt令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(), e.getUsername(),e.getName(), jwt);
        }
        //如果不存在员工，返回null
        return null;
    }


}























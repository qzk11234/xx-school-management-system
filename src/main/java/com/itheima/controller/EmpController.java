package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


//员工管理
@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;


    /**
     * 分页查询员工信息
     */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue="1") Integer page,
//                       @RequestParam(defaultValue="10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
//        log.info("{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
//        PageResult<Emp> pageResult  = empService.page(page,pageSize,name,gender,begin,end);
//
//        return Result.success(pageResult);
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);

        return Result.success(pageResult);
    }

    /**
     * 新增员工信息
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 删除员工信息
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据员工ID查询员工信息以及员工工作经历，然后返回给前端进行展示
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据员工ID查询员工信息：{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);


    }


    /**
     * 修改员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("{}", emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询全部员工数据
     */
    @GetMapping("/list")
    public Result listAll() {
        List<Emp> listAll = empService.listAll();
        return Result.success(listAll);
    }














}


































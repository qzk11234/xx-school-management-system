package com.itheima.controller;


import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import com.itheima.service.impl.ClazzServiceImpl;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clazzs")
@RestController
@Slf4j
public class ClazzController {

    @Autowired
    private ClazzServiceImpl clazzService;


    /**
     * 查询全部班级数据
     */
    @GetMapping
    public Result pageClazz(ClazzQueryParam clazzQueryParam){
        log.info("{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.pageClazz(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 删除班级数据
     */
    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable Integer id){
        log.info("删除班级数据{}", id);
        clazzService.deleteClazz(id);
        return Result.success();
    }


    /**
     * 添加班级数据
     */
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){
        log.info("添加班级数据{}", clazz);
        clazzService.addClazz(clazz);
        return Result.success();

    }
    /**
     * 根据ID查询班级数据
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询班级数据{}", id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }

    /**
     * 更新班级数据
     */
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz){
        log.info("更新班级数据{}", clazz);
        clazzService.updateClazz(clazz);
        return Result.success();
    }
    /**
     * 查询全部班级数据
     */
    @GetMapping("/list")
    public Result listClazz(){
        List<Clazz> clazzList = clazzService.listAll();
        return Result.success(clazzList);
    }





}

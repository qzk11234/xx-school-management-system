package com.itheima.controller;

import com.itheima.pojo.Operatelog;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志管理
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private EmpLogService empLogService;

    /**
     * 日志信息的分页查询
     */
    @GetMapping("/page")
    public Result pageLog(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("日志分页查询, page={}, pageSize={}", page, pageSize);
        PageResult<Operatelog> pageResult = empLogService.pageLog(page, pageSize);
        return Result.success(pageResult);
    }
}

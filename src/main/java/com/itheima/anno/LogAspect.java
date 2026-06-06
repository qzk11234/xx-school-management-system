package com.itheima.anno;

import com.itheima.mapper.EmpLogMapper;
import com.itheima.pojo.Operatelog;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 操作日志切面，自动拦截 @Log 注解的方法并记录日志
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 执行目标方法
        Object result = joinPoint.proceed();

        long costTime = System.currentTimeMillis() - start;

        try {
            Operatelog operatelog = new Operatelog();
            operatelog.setOperateEmpId(getCurrentEmpId());
            operatelog.setOperateTime(LocalDateTime.now());
            operatelog.setClassName(joinPoint.getTarget().getClass().getName());
            operatelog.setMethodName(joinPoint.getSignature().getName());
            operatelog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
            operatelog.setReturnValue(String.valueOf(result));
            operatelog.setCostTime((int) costTime);
            empLogMapper.insert(operatelog);
            log.info("记录操作日志成功");
        } catch (Exception e) {
            // 日志记录失败不影响主流程
            log.info("记录操作日志失败: {}", e.getMessage());
        }

        return result;
    }


    private Integer getCurrentEmpId() {
        return CurrentHolder.getCurrentId();
    }



}

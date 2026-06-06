package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operatelog {
    private Integer id; //ID
    private Integer operateEmpId; //操作员工ID
    private LocalDateTime operateTime; //操作时间
    private String className; //类名
    private String methodName; //方法名
    private String methodParams; //方法参数
    private String returnValue; //返回值
    private Integer costTime; //耗时(ms)
    private String info; //详细信息
    private String operateEmpName; //操作员工姓名（来自emp表）
}

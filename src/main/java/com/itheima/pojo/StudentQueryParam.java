package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class StudentQueryParam {
    private Integer page=1;  //当前页码
    private Integer pageSize=10;  //每页记录数
    private String name;  //学员姓名
    private Integer degree;  //学员学历
    private Integer clazzId;  //班级id



}

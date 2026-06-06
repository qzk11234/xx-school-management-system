package com.itheima.service.impl;


import com.itheima.anno.Log;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.DegreeOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Log
    @Override
    public JobOption getEmpJobData(){
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        List<Object> posList = list.stream().map(dataMap ->dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap ->dataMap.get("num")).toList();

        return new JobOption(posList, dataList);
    }

    @Log
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Log
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>> list = studentMapper.countStudentDegreeData();
        return list.stream().map(row -> {
            Map<String, Object> item = new java.util.LinkedHashMap<>();
            item.put("name", row.get("degree"));
            item.put("value", row.get("value"));
            return item;
        }).toList();
    }

    @Log
    @Override
    public DegreeOption getStudentCountData() {
        List<Map<String, Object>> list = studentMapper.countStudentCountData();

        List<Object> clazzList = list.stream().map(dataMap ->dataMap.get("clazz_id")).toList();
        List<Object> dataList = list.stream().map(dataMap ->dataMap.get("num")).toList();
        return new DegreeOption(clazzList, dataList);
    }
}

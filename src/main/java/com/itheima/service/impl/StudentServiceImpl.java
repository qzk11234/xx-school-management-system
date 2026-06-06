package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.anno.Log;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Log
    @Override
    public PageResult<Student> pageStudent(StudentQueryParam studentQueryParam) {
        // 从数据库中查询学员列表数据，并返回
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> rows = studentMapper.pageStudent(studentQueryParam);
        long total = ((Page<Student>) rows).getTotal();
        return new PageResult<>(total, rows);
    }

    /**
     * 删除学员
     * @param ids 删除的学员id，多个号分隔
     */
    @Log
    @Override
    public void deleteStudent(String ids) {
        studentMapper.deleteStudentById(ids);
    }

    @Log
    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        // 身份证号末尾小写x统一转为大写X
        if (student.getIdCard() != null && student.getIdCard().endsWith("x")) {
            student.setIdCard(student.getIdCard().toUpperCase());
        }
        studentMapper.insert(student);
    }

    /**
     * 根据ID查询学员信息
     * @param id 学员id
     * @return 学员信息
     */
    @Log
    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Log
    @Override
    public void updateStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        // 身份证号末尾小写x统一转为大写X
        if (student.getIdCard() != null && student.getIdCard().endsWith("x")) {
            student.setIdCard(student.getIdCard().toUpperCase());
        }
        studentMapper.updateStudent(student);
    }

    @Log
    @Override
    public void updateStudentViolation(Integer id, Integer score) {
        Student student = studentMapper.getStudentById(id);
            student.setViolationCount((short) (student.getViolationCount() + 1));
            student.setViolationScore((short) (student.getViolationScore() + score));
            student.setUpdateTime(LocalDateTime.now());
            studentMapper.updateStudent(student);

    }


}

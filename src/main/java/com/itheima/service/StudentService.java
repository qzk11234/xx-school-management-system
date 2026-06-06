package com.itheima.service;


import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

public interface StudentService {
    /**
     * 学员列表数据的条件分页查询
     *
     * @param studentQueryParam 查询条件
     * @return 分页结果
     */
    PageResult<Student> pageStudent(StudentQueryParam studentQueryParam);

    /**
     * 删除学员
     * @param ids 删除的学员id，多个号分隔
     */
    void deleteStudent(String ids);

    /**
     * 新增学员
     * @param student
     */
    void addStudent(Student student);

    /**
     * 根据ID查询学员信息
     * @param id 学员id
     * @return 学员信息
     */
    Student getStudentById(Integer id);

    /**
     * 修改学员信息
     */
    void updateStudent(Student student);

    /**
     * 修改学员违纪信息
     * @param id
     * @param score
     */
    void updateStudentViolation(Integer id, Integer score);
}

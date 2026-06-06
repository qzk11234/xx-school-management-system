package com.itheima.controller;


import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import com.itheima.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/students")
@RestController
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 学员列表数据的条件分页查询
     */
    @GetMapping
    public Result pageStudent(StudentQueryParam studentQueryParam){
        log.info("学生列表数据的条件分页查询，查询条件：{}", studentQueryParam);
        PageResult<Student> pageResult = studentService.pageStudent(studentQueryParam);
        return Result.success(pageResult);
    }
    /**
     * 删除学员
     */
    @DeleteMapping("/{ids}")
    public Result deleteStudent(@PathVariable String ids){
        log.info("删除学员，删除的学员id：{}", ids);
        studentService.deleteStudent(ids);
        return Result.success();
    }

    /**
     * 新增学员
     */
    @PostMapping
    public Result addStudent(@Valid @RequestBody Student student){
        log.info("新增学员，新增的学员信息：{}", student);
        studentService.addStudent(student);
        return Result.success();
    }
    /**
     * 根据ID查询学员信息
     */
    @GetMapping ("/{id}")
    public Result getStudent(@PathVariable Integer id){
        log.info("根据ID查询学员信息，查询的学员id：{}", id);
        Student student = studentService.getStudentById(id);
        return Result.success(student);
    }
    /**
     * 修改学员信息
     */
    @PutMapping()
    public Result updateStudent(@Valid @RequestBody Student student){
        log.info("修改学员信息，修改的学员信息：{}", student);
        studentService.updateStudent(student);
        return Result.success();
    }

    /**
     * 修改学员违纪信息
     */
    @PutMapping("violation/{id}/{score}")
    public Result updateStudentViolation(@PathVariable("id") Integer id, @PathVariable("score") Integer score){
        log.info("修改学员违纪信息，修改的学员id：{}，修改的违纪分：{}", id, score);
        studentService.updateStudentViolation(id, score);
        return Result.success();
    }






















}

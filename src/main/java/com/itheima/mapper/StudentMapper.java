package com.itheima.mapper;


import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 学员列表数据的条件分页查询
     *
     * @param studentQueryParam 查询条件
     * @return 学员列表数据
     */
    List<Student> pageStudent(StudentQueryParam studentQueryParam);

    /**
     * 删除学员
     */
    @Delete("DELETE FROM student WHERE id IN (${ids})")
    void deleteStudentById(String ids);

    /**
     * 新增学员
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO student (name, no, gender, phone,degree,clazz_id,id_card,is_college,address,graduation_date,create_time,update_time) " +
            "VALUES (#{name}, #{no}, #{gender}, #{phone}, #{degree}, #{clazzId}, #{idCard}, #{isCollege}, #{address}, #{graduationDate},#{createTime},#{updateTime})")
    void insert(Student student);

    /**
     * 根据ID查询学员信息
     */
    @Select("SELECT id, name, no, gender, phone,degree, id_card, is_college, address, graduation_date, violation_count, violation_score, clazz_id,create_time, update_time FROM student WHERE id = #{id}")
    Student getStudentById(Integer id);

    /**
     * 修改学员信息
     */@Update("UPDATE student SET name = #{name}, no = #{no}, gender = #{gender}, phone = #{phone}, degree = #{degree}, id_card = #{idCard}, is_college = #{isCollege}, address = #{address}, graduation_date = #{graduationDate},violation_count = #{violationCount},violation_score = #{violationScore},clazz_id = #{clazzId}, update_time = #{updateTime} WHERE id = #{id}")
    void updateStudent(Student student);

     /**
      * 统计学员的学历信息
      * @return
      */
    List<Map<String, Object>> countStudentDegreeData();

    /**
     * 统计每一个班级的人数
     */
    List<Map<String, Object>> countStudentCountData();
}

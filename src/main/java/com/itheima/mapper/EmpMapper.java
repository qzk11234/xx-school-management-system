package com.itheima.mapper;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.JobOption;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//操作员工信息
@Mapper
public interface EmpMapper {


    /**
     * 查询总记录数
     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public long count();

    /**
     * 分页查询
     */
//    @Select("select e.*, d.name as deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);




//    @Select("select e.*, d.name as deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")

    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp (username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    /**
     * 批量删除员工基本信息
     */
    void deleteBatch(List<Integer> ids);


    /**
     * 根据ID查询员工信息以及员工工作经历，然后返回给前端进行展示
     */
    Emp getById(Integer id);


    /**
     * 更新员工信息
     */
    void updateById(Emp emp);

    /**
     * 统计员工的岗位分布数据
     */

    List<Map<String,Object>> countEmpJobData();

    /**
     * 统计员工的性别分布数据
     */

    List<Map<String,Object>> countEmpGenderData();

    /**
     * 查询全部员工数据
     */
    @Select("select * from emp")
    List<Emp> listAll();

    /**
     * 登录查询
     */
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}


























package com.itheima.mapper;


import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 分页查询班级信息
     */
    public List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 删除班级数据
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 添加班级数据
     */
    @Insert("insert into clazz(name, room, begin_date, end_date,master_id,subject,create_time,update_time)" +
            "values(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);


    /**
     *根据ID查询班级数据
     */
    @Select("select * from clazz where id = #{id}")
    Clazz selectById(Integer id);

    /**
     * 更新班级数据
     */
    @Update("update clazz set name = #{name}, room = #{room}, begin_date = #{beginDate}, end_date = #{endDate}, master_id = #{masterId}, subject = #{subject}, update_time = #{updateTime} where id = #{id}")
    void updateById(Clazz clazz);

    /**
     * 查询全部班级数据
     */
    @Select("select * from clazz")
    List<Clazz> listAll();
}

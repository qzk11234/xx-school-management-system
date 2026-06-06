package com.itheima.mapper;

import com.itheima.pojo.Operatelog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpLogMapper {

    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    public void insert(Operatelog log);

    /**
     * 分页查询日志（关联emp表获取操作人姓名）
     */
    @Select("SELECT ol.*, e.name AS operate_emp_name FROM operate_log ol LEFT JOIN emp e ON ol.operate_emp_id = e.id ORDER BY ol.operate_time DESC")
    public List<Operatelog> pageLog();

}

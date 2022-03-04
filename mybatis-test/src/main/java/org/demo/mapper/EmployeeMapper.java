package org.demo.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.demo.po.Employee;

import java.util.List;

/**
 * @author Shawn
 * @version 1.0
 * @description: TODO
 * @date 2022/2/18 11:16
 */
public interface EmployeeMapper {


    Employee getEmpAndDepart(Integer id);

    List<Employee> getEmpAndDepart1();

    List<Employee> getEmpAndDepart2();

    @Select("select * from employee where id = #{id}")
    @Options
    Employee getEmpById(@Param("id") Integer id);
}

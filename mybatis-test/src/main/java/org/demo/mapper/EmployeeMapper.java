package org.demo.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;
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

    // MySQL 默认情况下，创建 prepareStatement 时，就已经是 ResultSet.TYPE_FORWARD_ONLY 和 ResultSet.CONCUR_READ_ONLY ，所以这两个参数可加可不加
    // @Select("select * from employee")
    // @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = Integer.MIN_VALUE)
    // @ResultType(Employee.class)
    // void getEmpList(ResultHandler<Employee> resultHandler);

    @Select("select * from employee limit #{limit}")
    Cursor<Employee> scan(@Param("limit") int limit);
}

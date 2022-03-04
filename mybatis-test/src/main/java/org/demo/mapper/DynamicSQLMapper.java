package org.demo.mapper;

import org.demo.po.Employee;

import java.util.List;

public interface DynamicSQLMapper {

    List<Employee> getEmpsByConditionIf(Employee employee);

    List<Employee> getEmpsByConditionIfWithWhere(Employee employee);

    Integer updateEmp(Employee employee);

    List<Employee> selectEmployeeBySql();
}

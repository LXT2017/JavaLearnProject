package org.demo.mapper;

import org.demo.po.Employee;

import java.util.List;

/**
 * @author Shawn
 * @version 1.0
 * @description: TODO
 * @date 2022/2/18 11:16
 */
public interface EmployeeMapper1 {


    List<Employee> getEmpsWithDiscriminator();

}

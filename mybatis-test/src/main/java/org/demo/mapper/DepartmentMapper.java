package org.demo.mapper;

import org.demo.po.Department;

public interface DepartmentMapper {

    Department MyDept(Integer id);

    Department getDeptStep(Integer id);
}

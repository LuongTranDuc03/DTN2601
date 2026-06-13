package com.vti.service;

import com.vti.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();

    Department findById(Integer id);

    Department findByName(String name);

    Department create (Department department);

    Department update(Department department);

    Department update(Integer id, Department department);

    void delete(Integer id);
}

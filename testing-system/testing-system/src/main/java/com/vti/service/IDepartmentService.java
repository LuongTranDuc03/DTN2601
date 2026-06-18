package com.vti.service;

import com.vti.entity.Department;
import com.vti.form.DepartmentCreateForm;
import com.vti.form.DepartmentUpdateForm;
import com.vti.result.DepartmentDTO;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();

    Department findById(Integer id);

    Department findByName(String name);

    DepartmentDTO create(DepartmentCreateForm form);

    DepartmentDTO update(Integer id, DepartmentUpdateForm form);

    void delete(Integer id);
}

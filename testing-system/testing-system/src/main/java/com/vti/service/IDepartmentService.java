package com.vti.service;

import com.vti.entity.Department;
import com.vti.form.DepartmentCreateForm;
import com.vti.form.DepartmentUpdateForm;
import com.vti.result.DepartmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService {
    Page<DepartmentDTO> findAll(Pageable pageable);

    Department findById(Integer id);

    Department findByName(String name);

    DepartmentDTO create(DepartmentCreateForm form);

    DepartmentDTO update(Integer id, DepartmentUpdateForm form);

    void delete(Integer id);
}

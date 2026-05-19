package com.zalo.auto.backend.service.impl;

import com.zalo.auto.backend.repository.IDepartmentRepository;
import com.zalo.auto.backend.repository.impl.DepartmentRepositoryImplement;
import com.zalo.auto.backend.service.IDepartmentSevice;
import com.zalo.auto.entity.Department;

import java.util.List;

public class DepartmentServiceImplement implements IDepartmentSevice {
    IDepartmentRepository departmentRepository = new DepartmentRepositoryImplement();

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public boolean update(int id, String newName) {
        return departmentRepository.update(id, newName);
    }

    @Override
    public boolean delete(int id) {
        return departmentRepository.delete(id);
    }

    @Override
    public boolean create(String name) {
        return departmentRepository.create(name);
    }

    @Override
    public List<Department> findMostEmployees() {
        return departmentRepository.findMostEmployees();
    }

    @Override
    public List<Department> findLeastEmployees() {
        return departmentRepository.findLeastEmployees();
    }

    @Override
    public boolean checkIfExistName(String name) {
        return departmentRepository.checkExistName(name);
    }
}

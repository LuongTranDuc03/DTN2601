package com.zalo.auto.backend.controller;

import com.zalo.auto.backend.service.IDepartmentSevice;
import com.zalo.auto.backend.service.impl.DepartmentServiceImplement;
import com.zalo.auto.entity.Department;

import java.util.List;

public class DepartmentController {
    IDepartmentSevice departmentSevice = new DepartmentServiceImplement();

    public List<Department> findAll() {
        return departmentSevice.findAll();
    }

    public Department findById(int id) {
        return departmentSevice.findById(id);
    }

    public boolean update(int id, String newName) {
        return departmentSevice.update(id, newName);
    }

    public boolean delete(int id) {
        return departmentSevice.delete(id);
    }

    public boolean create(String name) {
        return departmentSevice.create(name);
    }

    public List<Department> findMostEmployees() {
        return departmentSevice.findMostEmployees();
    }

    public List<Department> findLeastEmployees() {
        return departmentSevice.findLeastEmployees();
    }
}

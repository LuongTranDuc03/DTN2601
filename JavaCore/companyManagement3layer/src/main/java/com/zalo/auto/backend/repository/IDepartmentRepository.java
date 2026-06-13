package com.zalo.auto.backend.repository;

import com.zalo.auto.entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
    Department findById(int id);
    boolean update(int id, String newName);
    boolean delete(int id);
    boolean create(String name);
    List<Department> findMostEmployees();
    List<Department> findLeastEmployees();
}

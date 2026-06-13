package com.vti.repository;

import com.vti.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department,Integer> {

    Department findByName(String name);

    Department existsByName(String name);

    List<Department> findAllByOrderByNameAsc();
}

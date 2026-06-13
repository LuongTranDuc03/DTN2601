package com.vti.controller;

import com.vti.entity.Department;
import com.vti.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService DepartmentService;

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        List<Department> departments = DepartmentService.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Department> findById(@PathVariable(name = "id") Integer id) {
        Department department = DepartmentService.findById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<Department> findByName(@RequestParam(name = "name") String name) {
        Department department = DepartmentService.findByName(name);
        return new ResponseEntity<>(department, HttpStatus.OK); // 200
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Department department) {
        Department createdDepartment = DepartmentService.create(department);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED); // 201
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody Department department) {
        Department updatedDepartment = DepartmentService.update(id, department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        DepartmentService.delete(id);
        return new ResponseEntity<>("Delete success!", HttpStatus.OK);
    }
}

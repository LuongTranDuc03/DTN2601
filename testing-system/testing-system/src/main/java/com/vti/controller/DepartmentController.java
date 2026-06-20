package com.vti.controller;

import com.vti.entity.Department;
import com.vti.form.DepartmentCreateForm;
import com.vti.form.DepartmentUpdateForm;
import com.vti.result.DepartmentDTO;
import com.vti.service.IDepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<DepartmentDTO>> findAll(Pageable pageable) {
        Page<DepartmentDTO> departments = DepartmentService.findAll(pageable);
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
    public ResponseEntity<DepartmentDTO> create(@Valid @RequestBody DepartmentCreateForm form) {
        DepartmentDTO createdDepartment = DepartmentService.create(form);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED); // 201
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DepartmentDTO> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody DepartmentUpdateForm form) {
        DepartmentDTO updatedDepartment = DepartmentService.update(id, form);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        DepartmentService.delete(id);
        return new ResponseEntity<>("Delete success!", HttpStatus.OK);
    }
}

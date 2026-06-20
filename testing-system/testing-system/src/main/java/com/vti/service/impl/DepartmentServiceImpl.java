package com.vti.service.impl;

import com.vti.entity.Department;
import com.vti.form.DepartmentCreateForm;
import com.vti.form.DepartmentUpdateForm;
import com.vti.repository.IDepartmentRepository;
import com.vti.result.DepartmentDTO;
import com.vti.service.IDepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequestMapping
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired //Khoi tao doi tuong
    private IDepartmentRepository DepartmentRepository;

    @Override
    public Page<DepartmentDTO> findAll(Pageable pageable) {
        Page<Department> departments = DepartmentRepository.findAll(pageable);
        return departments.map(dept -> new DepartmentDTO(dept));
    }

    @Override
    public Department findById(Integer id) {
        Optional<Department> optional = DepartmentRepository.findById(id);
//        if (optional.isPresent()) {
//            Department department = optional.get();
//            return department;
//        } else {
//            return null;
//        }
        return optional.orElse(null);
    }

    @Override
    public Department findByName(String name) {
        Department department = DepartmentRepository.findByName(name);
        return department;
    }

    @Override
    public DepartmentDTO create(DepartmentCreateForm form) {
        Department department = new Department();
        department.setName(form.getName());
        Department saved = DepartmentRepository.save(department);
        return new DepartmentDTO(saved);
    }

    @Override
    public DepartmentDTO update(Integer id, DepartmentUpdateForm form) {
        Department departmentUpdate = DepartmentRepository.findById(id).orElse(null);

        if (Objects.isNull(departmentUpdate)) {
            throw new RuntimeException("ID khong ton tai");
        }

        departmentUpdate.setName(form.getName());
        Department saved = DepartmentRepository.save(departmentUpdate);
        return new DepartmentDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        if (!DepartmentRepository.existsById(id)) {
            throw new RuntimeException("ID khong ton tai");
        }
        DepartmentRepository.deleteById(id);
    }
}

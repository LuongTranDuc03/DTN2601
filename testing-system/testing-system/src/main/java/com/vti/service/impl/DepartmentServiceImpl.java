package com.vti.service.impl;

import com.vti.entity.Department;
import com.vti.repository.IDepartmentRepository;
import com.vti.service.IDepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Department> findAll() {
        return  DepartmentRepository.findAll();
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
    public Department create(Department department) {
        Department department1 = DepartmentRepository.save(department);
        return department1;
    }

    @Override
    public Department update(Department department) {
        if (department.getId() == null || !DepartmentRepository.existsById(department.getId())) {
            throw new RuntimeException("ID khong ton tai");
        }
        return DepartmentRepository.save(department);
    }

    @Override
    public Department update(Integer id, Department department) {
        Department departmentUpdate = DepartmentRepository.findById(id).orElse(null);

        if(Objects.isNull(departmentUpdate)){
            throw new RuntimeException("ID khong ton tai");
        }

        departmentUpdate.setName(department.getName());
        return DepartmentRepository.save(departmentUpdate);
    }

    @Override
    public void delete(Integer id) {
        if (!DepartmentRepository.existsById(id)) {
            throw new RuntimeException("ID khong ton tai");
        }
        DepartmentRepository.deleteById(id);
    }
}

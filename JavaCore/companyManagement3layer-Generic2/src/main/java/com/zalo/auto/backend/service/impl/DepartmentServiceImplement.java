package com.zalo.auto.backend.service.impl;

import com.zalo.auto.backend.repository.IDepartmentRepository;
import com.zalo.auto.backend.repository.impl.DepartmentRepositoryImplement;
import com.zalo.auto.backend.service.IDepartmentSevice;
import com.zalo.auto.backend.service.csv.DepartmentCsvImport;
import com.zalo.auto.dto.ImportError;
import com.zalo.auto.dto.context.DepartmentContext;
import com.zalo.auto.dto.csv.DepartmentCsv;
import com.zalo.auto.entity.Department;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public String importDepartmentToCSV(String pahtName) {
        // Lấy danh sách tên phòng ban từ DB 1 lần duy nhất để tối ưu hiệu năng
        List<Department> existingDepts = departmentRepository.findAll();
        Map<String, Department> existingNamesMap = new HashMap<>();
        for (Department d : existingDepts) {
            existingNamesMap.put(d.getDepartmentName().toLowerCase(), d);
        }

        DepartmentContext context = new DepartmentContext(existingNamesMap);

        DepartmentCsvImport departmentCsvImport = new DepartmentCsvImport();
        return departmentCsvImport.importFile(pahtName, context);
    }

    @Override
    public List<DepartmentCsv> readFile(String path) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readFile'");
    }

    @Override
    public void validation(DepartmentCsv t, DepartmentContext e, List<ImportError> importErrors,
                           List<Department> entityList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validation'");
    }

    @Override
    public void saveAll(List<Department> entityList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public void exportFileError(List<ImportError> importErrors) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exportFileError'");
    }
}

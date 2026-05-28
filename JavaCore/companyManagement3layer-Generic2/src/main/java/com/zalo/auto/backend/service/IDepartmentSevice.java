package com.zalo.auto.backend.service;

import com.zalo.auto.dto.ImportError;
import com.zalo.auto.dto.context.DepartmentContext;
import com.zalo.auto.dto.csv.DepartmentCsv;
import com.zalo.auto.entity.Department;

import java.io.FileNotFoundException;
import java.util.List;

public interface IDepartmentSevice {
    List<Department> findAll();
    Department findById(int id);
    boolean update(int id, String newName);
    boolean delete(int id);
    boolean create(String name);
    List<Department> findMostEmployees();
    List<Department> findLeastEmployees();

    boolean checkIfExistName(String name);

    String importDepartmentToCSV(String pahtName) throws FileNotFoundException;

    List<DepartmentCsv> readFile(String path);

    void validation(DepartmentCsv t, DepartmentContext e, List<ImportError> importErrors,
                    List<Department> entityList);

    void saveAll(List<Department> entityList);

    void exportFileError(List<ImportError> importErrors);
}

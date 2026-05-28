package com.zalo.auto.backend.service.csv;

import com.zalo.auto.backend.repository.IDepartmentRepository;
import com.zalo.auto.backend.repository.impl.DepartmentRepositoryImplement;
import com.zalo.auto.backend.service.IImportFile;
import com.zalo.auto.dto.ImportError;
import com.zalo.auto.dto.context.DepartmentContext;
import com.zalo.auto.dto.csv.DepartmentCsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DepartmentCsvImport implements IImportFile<DepartmentCsv, DepartmentContext, String> {

    private final IDepartmentRepository departmentRepository = new DepartmentRepositoryImplement();
    private String currentFilePath;

    @Override
    public List<DepartmentCsv> readFile(String path) {
        this.currentFilePath = path;
        List<DepartmentCsv> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String header = bufferedReader.readLine(); // Skip header
            String line = bufferedReader.readLine();
            int lineNumber = 2;

            while (line != null) {
                String[] split = line.split(",");
                if (split.length > 0) {
                    String departmentName = split[0].trim();
                    result.add(new DepartmentCsv(departmentName, lineNumber));
                }
                line = bufferedReader.readLine();
                lineNumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void validation(DepartmentCsv csvRow, DepartmentContext context, List<ImportError> importErrors, List<String> entityList) {
        String departmentName = csvRow.getName();
        String departmentNameLower = departmentName.toLowerCase();
        int lineNumber = csvRow.getLineNumber();

        if (departmentName.isEmpty()) {
            importErrors.add(new ImportError(lineNumber, departmentName, "Tên phòng ban bị trống"));
        } else if (context.getCsvNamesMapLower().containsKey(departmentNameLower)) {
            importErrors.add(new ImportError(lineNumber, departmentName, "Tên phòng ban bị trùng lặp trong file CSV"));
        } else if (context.getExistingNamesMap().containsKey(departmentNameLower)) {
            importErrors.add(new ImportError(lineNumber, departmentName, "Tên phòng ban đã tồn tại trong database"));
        } else {
            entityList.add(departmentName);
            context.getCsvNamesMapLower().put(departmentNameLower, true);
        }
    }

    @Override
    public void saveAll(List<String> entityList) {
        // We will call this explicitly if no errors
        departmentRepository.createDepartments(entityList);
    }

    @Override
    public void exportFileError(List<ImportError> importErrors) {
        try {
            File originalFile = new File(currentFilePath);
            String parentPath = originalFile.getParent();
            String errorPath = (parentPath != null ? parentPath + File.separator : "") + "import_errors.txt";

            File errorFile = new File(errorPath);
            try (FileWriter writer = new FileWriter(errorFile)) {
                writer.write("====== DANH SÁCH LỖI IMPORT ======\n");
                for (ImportError err : importErrors) {
                    writer.write(err.toString() + "\n");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String exportSuccessFile(List<String> entityList) {
        try {
            File originalFile = new File(currentFilePath);
            String parentPath = originalFile.getParent();
            String outputPath = (parentPath != null ? parentPath + File.separator : "") + "imported_success_departments.txt";

            File newFile = new File(outputPath);
            try (FileWriter writer = new FileWriter(newFile)) {
                writer.write("====== DANH SÁCH PHÒNG BAN IMPORT THÀNH CÔNG ======\n");
                for (String dep : entityList) {
                    writer.write("- " + dep + "\n");
                }
            }
            return "✅ Import thành công " + entityList.size() + " phòng ban.\nĐã lưu danh sách thành công ra file:\n" + newFile.getAbsolutePath();
        } catch (Exception ex) {
            return "✅ Import thành công " + entityList.size() + " phòng ban.\nNhưng có lỗi khi tạo file hiển thị: " + ex.getMessage();
        }
    }

    @Override
    public String importFile(String path, DepartmentContext context) {
        if (!path.toLowerCase().endsWith(".csv")) {
            return "❌ Lỗi: File không đúng định dạng. Vui lòng chọn file có đuôi .csv!";
        }

        File file = new File(path);
        if (!file.exists()) {
            return "❌ Lỗi: Không tìm thấy file: " + path;
        }

        List<DepartmentCsv> csvList = readFile(path);
        if (csvList.isEmpty()) {
            return "❌ File CSV rỗng hoặc không có dữ liệu hợp lệ để import.";
        }

        List<ImportError> importErrors = new ArrayList<>();
        List<String> entityList = new ArrayList<>();
        
        for (DepartmentCsv entity : csvList) {
            validation(entity, context, importErrors, entityList);
        }

        if (!importErrors.isEmpty()) {
            exportFileError(importErrors);
            try {
                File originalFile = new File(path);
                String parentPath = originalFile.getParent();
                String errorPath = (parentPath != null ? parentPath + File.separator : "") + "import_errors.txt";
                return "❌ Import thất bại do dữ liệu không hợp lệ!\nĐã lưu chi tiết lỗi ra file:\n" + new File(errorPath).getAbsolutePath();
            } catch (Exception ex) {
                return "❌ Import thất bại! Lỗi sinh ra trong quá trình xuất file báo lỗi.";
            }
        }

        if (entityList.isEmpty()) {
            return "❌ File CSV không có dữ liệu hợp lệ để import.";
        }

        boolean isSuccess = departmentRepository.createDepartments(entityList);
        if (isSuccess) {
            return exportSuccessFile(entityList);
        } else {
            return "❌ Import thất bại! Dữ liệu đã được rollback (Lỗi database).";
        }
    }
}

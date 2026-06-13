package com.zalo.auto.backend.service.csv;

import com.zalo.auto.backend.repository.IAccountRepository;
import com.zalo.auto.backend.repository.impl.AccountRepositoryImplement;
import com.zalo.auto.backend.service.IImportFile;
import com.zalo.auto.dto.ImportError;
import com.zalo.auto.dto.context.AccountContext;
import com.zalo.auto.dto.csv.AccountCsv;
import com.zalo.auto.entity.Account;
import com.zalo.auto.entity.Department;
import com.zalo.auto.entity.Position;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountCsvImport implements IImportFile<AccountCsv, AccountContext, Account> {

    private final IAccountRepository accountRepository = new AccountRepositoryImplement();
    private String currentFilePath;

    @Override
    public List<AccountCsv> readFile(String path) {
        this.currentFilePath = path;
        List<AccountCsv> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String header = bufferedReader.readLine(); // Skip header
            String line = bufferedReader.readLine();
            int lineNumber = 2;

            while (line != null) {
                String[] split = line.split(",");
                // Expecting at least 5 columns: email, password, fullName, departmentId, positionId
                if (split.length >= 5) {
                    String email = split[0].trim();
                    String password = split[1].trim();
                    String fullName = split[2].trim();
                    String departmentId = split[3].trim();
                    String positionId = split[4].trim();

                    result.add(new AccountCsv(email, password, fullName, departmentId, positionId, lineNumber));
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
    public void validation(AccountCsv csvRow, AccountContext context, List<ImportError> importErrors, List<Account> entityList) {
        int lineNumber = csvRow.getLineNumber();
        String email = csvRow.getEmail();
        String password = csvRow.getPassword();
        String fullName = csvRow.getFullName();
        String deptRef = csvRow.getDepartmentId();
        String posRef = csvRow.getPositionId();

        // 1. Validate Email
        if (email.isEmpty()) {
            importErrors.add(new ImportError(lineNumber, email, "Email bị trống"));
            return;
        } else if (context.getCsvEmailsMapLower().containsKey(email.toLowerCase())) {
            importErrors.add(new ImportError(lineNumber, email, "Email bị trùng lặp trong file CSV"));
            return;
        } else if (context.getMapAccountByEmail().containsKey(email.toLowerCase())) {
            importErrors.add(new ImportError(lineNumber, email, "Email đã tồn tại trong database"));
            return;
        }

        // 2. Validate Password
        if (password.isEmpty()) {
            importErrors.add(new ImportError(lineNumber, "password", "Password không được để trống"));
            return;
        }

        // 3. Validate FullName
        if (fullName.isEmpty()) {
            importErrors.add(new ImportError(lineNumber, "fullName", "Họ tên không được để trống"));
            return;
        }

        // 4. Validate Department
        if (deptRef.isEmpty()) {
            importErrors.add(new ImportError(lineNumber, "department", "Mã/Tên phòng ban không được để trống"));
            return;
        }
        Department MatchedDept = null;
        // Thử tìm theo ID trước
        try {
            int dId = Integer.parseInt(deptRef);
            for (Department d : context.getDepartments()) {
                if (d.getDepartmentId() == dId) {
                    MatchedDept = d;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            // Nếu không phải ID số, tìm theo tên
            for (Department d : context.getDepartments()) {
                if (d.getDepartmentName().equalsIgnoreCase(deptRef)) {
                    MatchedDept = d;
                    break;
                }
            }
        }
        if (MatchedDept == null) {
            importErrors.add(new ImportError(lineNumber, deptRef, "Phòng ban không tồn tại trong hệ thống"));
            return;
        }

        // 5. Validate Position
        if (posRef.isEmpty()) {
            importErrors.add(new ImportError(lineNumber, "position", "Mã/Tên chức vụ không được để trống"));
            return;
        }
        Position MatchedPos = null;
        // Thử tìm theo ID trước
        try {
            int pId = Integer.parseInt(posRef);
            for (Position p : context.getPositions()) {
                if (p.getPositionId() == pId) {
                    MatchedPos = p;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            // Nếu không phải ID số, tìm theo tên
            for (Position p : context.getPositions()) {
                if (p.getPositionName().toString().equalsIgnoreCase(posRef)) {
                    MatchedPos = p;
                    break;
                }
            }
        }
        if (MatchedPos == null) {
            importErrors.add(new ImportError(lineNumber, posRef, "Chức vụ không tồn tại trong hệ thống"));
            return;
        }

        // Nếu tất cả hợp lệ, tạo thực thể Account và đánh dấu đã sử dụng trong Context
        Account newAcc = new Account(0, email, password, fullName, MatchedDept, MatchedPos, LocalDate.now());
        entityList.add(newAcc);
        context.getCsvEmailsMapLower().put(email.toLowerCase(), true);
    }

    @Override
    public void saveAll(List<Account> entityList) {
        accountRepository.createAccounts(entityList);
    }

    @Override
    public void exportFileError(List<ImportError> importErrors) {
        try {
            File originalFile = new File(currentFilePath);
            String parentPath = originalFile.getParent();
            String errorPath = (parentPath != null ? parentPath + File.separator : "") + "import_errors.txt";

            File errorFile = new File(errorPath);
            try (FileWriter writer = new FileWriter(errorFile)) {
                writer.write("====== DANH SÁCH LỖI IMPORT ACCOUNT ======\n");
                for (ImportError err : importErrors) {
                    writer.write(err.toString() + "\n");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String exportSuccessFile(List<Account> entityList) {
        try {
            File originalFile = new File(currentFilePath);
            String parentPath = originalFile.getParent();
            String outputPath = (parentPath != null ? parentPath + File.separator : "") + "imported_success_accounts.txt";

            File newFile = new File(outputPath);
            try (FileWriter writer = new FileWriter(newFile)) {
                writer.write("====== DANH SÁCH NHÂN VIÊN IMPORT THÀNH CÔNG ======\n");
                for (Account acc : entityList) {
                    writer.write(String.format("- %s (%s) - PB: %s, CV: %s\n", 
                        acc.getFullName(), acc.getEmail(), 
                        acc.getDepartment().getDepartmentName(), 
                        acc.getPosition().getPositionName()));
                }
            }
            return "✅ Import thành công " + entityList.size() + " nhân viên.\nĐã lưu danh sách thành công ra file:\n" + newFile.getAbsolutePath();
        } catch (Exception ex) {
            return "✅ Import thành công " + entityList.size() + " nhân viên.\nNhưng có lỗi khi tạo file hiển thị: " + ex.getMessage();
        }
    }

    @Override
    public String importFile(String path, AccountContext context) {
        if (!path.toLowerCase().endsWith(".csv")) {
            return "❌ Lỗi: File không đúng định dạng. Vui lòng chọn file có đuôi .csv!";
        }

        File file = new File(path);
        if (!file.exists()) {
            return "❌ Lỗi: Không tìm thấy file: " + path;
        }

        List<AccountCsv> csvList = readFile(path);
        if (csvList.isEmpty()) {
            return "❌ File CSV rỗng hoặc không có dữ liệu hợp lệ để import.";
        }

        List<ImportError> importErrors = new ArrayList<>();
        List<Account> entityList = new ArrayList<>();

        for (AccountCsv entity : csvList) {
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

        boolean isSuccess = accountRepository.createAccounts(entityList);
        if (isSuccess) {
            return exportSuccessFile(entityList);
        } else {
            return "❌ Import thất bại! Dữ liệu đã được rollback (Lỗi database).";
        }
    }
}

package com.zalo.auto.frontend;

import com.zalo.auto.backend.controller.AccountController;
import com.zalo.auto.backend.controller.DepartmentController;
import com.zalo.auto.backend.controller.PositionController;
import com.zalo.auto.entity.Account;

import java.util.List;
import java.util.Scanner;

public class AccountFunction {
    private AccountController accountController;
    private DepartmentController departmentController;
    private PositionController positionController;
    private Scanner scanner;

    public AccountFunction() {
        accountController = new AccountController();
        departmentController = new DepartmentController();
        positionController = new PositionController();
        scanner = new Scanner(System.in);
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ NHÂN VIÊN ---");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật (theo ID)");
            System.out.println("4. Xóa (theo ID)");
            System.out.println("5. Tìm kiếm (theo ID)");
            System.out.println("6. Import dữ liệu từ file .csv");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn chức năng: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: displayAll(); break;
                    case 2: create(); break;
                    case 3: update(); break;
                    case 4: delete(); break;
                    case 5: searchById(); break;
                    case 6: importFile(); break;
                    case 0: return;
                    default: System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập một số nguyên!");
            }
        }
    }

    private void displayAll() {
        List<Account> list = accountController.findAll();
        System.out.println("\n=============================================== DANH SÁCH NHÂN VIÊN ===============================================");
        System.out.printf("%-5s %-25s %-20s %-15s %-15s %-12s\n", "ID", "Email", "Họ Tên", "Phòng Ban", "Chức Vụ", "Ngày Tạo");
        if (list.isEmpty()) {
            System.out.println("                                     (Trống)                                     ");
        } else {
            for (Account a : list) {
                System.out.printf("%-5d %-25s %-20s %-15s %-15s %-12s\n",
                    a.getAccountId(), 
                    a.getEmail(), 
                    a.getFullName(),
                    a.getDepartment() != null ? a.getDepartment().getDepartmentName() : "N/A",
                    a.getPosition() != null ? a.getPosition().getPositionName() : "N/A",
                    a.getCreateDate());
            }
        }
    }

    private void create() {
        String username;
        while (true) {
            System.out.print("Nhập Username: ");
            username = scanner.nextLine();
            if (username == null || username.trim().isEmpty()) {
                System.out.println("❌ Username không được để trống!");
                continue;
            }
            if (accountController.checkExistUsername(username)) {
                System.out.println("❌ Username đã tồn tại!");
                continue;
            }
            break;
        }

        String email;
        while (true) {
            System.out.print("Nhập Email: ");
            email = com.zalo.auto.utils.ScannerUtils.inputEmail();
            if (accountController.checkExistEmail(email)) {
                System.out.println("❌ Email đã tồn tại!");
                continue;
            }
            break;
        }

        String name;
        while (true) {
            System.out.print("Nhập Họ Tên: ");
            name = scanner.nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.out.println("❌ Họ Tên không được để trống!");
                continue;
            }
            break;
        }

        int dId;
        while (true) {
            try {
                System.out.print("Nhập ID Phòng Ban: ");
                dId = Integer.parseInt(scanner.nextLine());
                if (departmentController.findById(dId) == null) {
                    System.out.println("❌ ID Phòng Ban không tồn tại!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID phải là số nguyên!");
            }
        }

        int pId;
        while (true) {
            try {
                System.out.print("Nhập ID Chức Vụ: ");
                pId = Integer.parseInt(scanner.nextLine());
                if (positionController.findById(pId) == null) {
                    System.out.println("❌ ID Chức Vụ không tồn tại!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID phải là số nguyên!");
            }
        }

        if (accountController.create(email, username, name, dId, pId)) {
            System.out.println("✅ Thêm thành công!");
        } else {
            System.out.println("❌ Thêm thất bại!");
        }
    }

    private void update() {
        int id;
        while (true) {
            try {
                System.out.print("Nhập ID nhân viên cần sửa: ");
                id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    System.out.println("❌ ID phải lớn hơn 0!");
                    continue;
                }
                if (accountController.findById(id) == null) {
                    System.out.println("❌ ID nhân viên không tồn tại!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID phải là số nguyên!");
            }
        }

        String username;
        while (true) {
            System.out.print("Nhập Username mới: ");
            username = scanner.nextLine();
            if (username == null || username.trim().isEmpty()) {
                System.out.println("❌ Username không được để trống!");
                continue;
            }
            if (accountController.checkExistUsername(username)) {
                Account existing = accountController.findById(id);
                if (existing.getUsername() == null || !existing.getUsername().equals(username)) {
                    System.out.println("❌ Username đã tồn tại!");
                    continue;
                }
            }
            break;
        }

        String email;
        while (true) {
            System.out.print("Nhập Email mới: ");
            email = com.zalo.auto.utils.ScannerUtils.inputEmail();
            if (accountController.checkExistEmail(email)) {
                Account existing = accountController.findById(id);
                if (existing.getEmail() == null || !existing.getEmail().equals(email)) {
                    System.out.println("❌ Email đã tồn tại!");
                    continue;
                }
            }
            break;
        }

        String name;
        while (true) {
            System.out.print("Nhập Họ Tên mới: ");
            name = scanner.nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.out.println("❌ Họ Tên không được để trống!");
                continue;
            }
            break;
        }

        int dId;
        while (true) {
            try {
                System.out.print("Nhập ID Phòng Ban mới: ");
                dId = Integer.parseInt(scanner.nextLine());
                if (departmentController.findById(dId) == null) {
                    System.out.println("❌ ID Phòng Ban không tồn tại!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID phải là số nguyên!");
            }
        }

        int pId;
        while (true) {
            try {
                System.out.print("Nhập ID Chức Vụ mới: ");
                pId = Integer.parseInt(scanner.nextLine());
                if (positionController.findById(pId) == null) {
                    System.out.println("❌ ID Chức Vụ không tồn tại!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID phải là số nguyên!");
            }
        }

        if (accountController.update(id, email, username, name, dId, pId)) {
            System.out.println("✅ Cập nhật thành công!");
        } else {
            System.out.println("❌ Cập nhật thất bại!");
        }
    }

    private void delete() {
        int id;
        while (true) {
            try {
                System.out.print("Nhập ID nhân viên cần xóa: ");
                id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    System.out.println("❌ ID phải lớn hơn 0!");
                    continue;
                }
                if (accountController.findById(id) == null) {
                    System.out.println("❌ ID không tồn tại!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID phải là số nguyên!");
            }
        }

        if (accountController.delete(id)) {
            System.out.println("✅ Xóa thành công!");
        } else {
            System.out.println("❌ Xóa thất bại!");
        }
    }

    private void searchById() {
        try {
            System.out.print("Nhập ID nhân viên cần tìm: ");
            int id = Integer.parseInt(scanner.nextLine());
            Account a = accountController.findById(id);
            if (a != null) {
                System.out.println("\n--- THÔNG TIN NHÂN VIÊN ---");
                System.out.println(a);
            } else {
                System.out.println("❌ Không tìm thấy nhân viên có ID = " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi: ID phải là số nguyên!");
        }
    }

    private void importFile() {
        System.out.print("Nhập đường dẫn file .csv cần import (ví dụ: account.csv): ");
        String path = scanner.nextLine();
        if (path == null || path.trim().isEmpty()) {
            System.out.println("❌ Đường dẫn không được để trống!");
            return;
        }
        String resultMessage = accountController.importAccountToCSV(path.trim());
        System.out.println(resultMessage);
    }
}

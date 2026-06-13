package com.zalo.auto.frontend;

import com.zalo.auto.backend.controller.AccountController;
import com.zalo.auto.entity.Account;

import java.util.List;
import java.util.Scanner;

public class AccountFunction {
    private AccountController accountController;
    private Scanner scanner;

    public AccountFunction() {
        accountController = new AccountController();
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
        System.out.println("\n==================================== DANH SÁCH NHÂN VIÊN ====================================");
        System.out.printf("%-5s %-25s %-20s %-15s %-15s %-12s\n", "ID", "Email", "Họ Tên", "Phòng Ban", "Chức Vụ", "Ngày Tạo");
        if (list.isEmpty()) {
            System.out.println("                                     (Trống)                                     ");
        } else {
            for (Account a : list) {
                System.out.printf("%-5d %-25s %-20s %-15s %-15s %-12s\n",
                    a.getAccountId(), a.getEmail(), a.getFullName(),
                    a.getDepartment() != null ? a.getDepartment().getDepartmentName() : "N/A",
                    a.getPosition() != null ? a.getPosition().getPositionName() : "N/A",
                    a.getCreateDate());
            }
        }
    }

    private void create() {
        try {
            System.out.print("Nhập Email: ");
            String email = scanner.nextLine();
            System.out.print("Nhập Họ Tên: ");
            String name = scanner.nextLine();
            System.out.print("Nhập ID Phòng Ban: ");
            int dId = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập ID Chức Vụ: ");
            int pId = Integer.parseInt(scanner.nextLine());

            if (accountController.create(email, name, dId, pId)) {
                System.out.println("✅ Thêm thành công!");
            } else {
                System.out.println("❌ Thêm thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi: ID phải là số nguyên!");
        }
    }

    private void update() {
        try {
            System.out.print("Nhập ID nhân viên cần sửa: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập Email mới: ");
            String email = scanner.nextLine();
            System.out.print("Nhập Họ Tên mới: ");
            String name = scanner.nextLine();
            System.out.print("Nhập ID Phòng Ban mới: ");
            int dId = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập ID Chức Vụ mới: ");
            int pId = Integer.parseInt(scanner.nextLine());

            if (accountController.update(id, email, name, dId, pId)) {
                System.out.println("✅ Cập nhật thành công!");
            } else {
                System.out.println("❌ Cập nhật thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi: ID phải là số nguyên!");
        }
    }

    private void delete() {
        try {
            System.out.print("Nhập ID nhân viên cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (accountController.delete(id)) {
                System.out.println("✅ Xóa thành công!");
            } else {
                System.out.println("❌ Xóa thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi: ID phải là số nguyên!");
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
}

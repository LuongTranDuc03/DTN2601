package com.zalo.auto.frontend;

import com.zalo.auto.backend.controller.DepartmentController;
import com.zalo.auto.entity.Department;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DepartmentFunction {
    private DepartmentController  departmentController = new DepartmentController();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ PHÒNG BAN ---");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật (theo ID)");
            System.out.println("4. Xóa (theo ID)");
            System.out.println("5. Phòng ban nhiều nhân viên nhất");
            System.out.println("6. Phòng ban ít nhân viên nhất");
            System.out.println("7. Tìm kiếm theo ID");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn chức năng: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: displayAll(); break;
                case 2: create(); break;
                case 3: update(); break;
                case 4: delete(); break;
                case 5: displayMostEmployees(); break;
                case 6: displayLeastEmployees(); break;
                case 7: findById(); break;
                case 0: return;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void displayAll() {
        List<Department> list = departmentController.findAll();
        System.out.println("\n========== DANH SÁCH PHÒNG BAN ==========");
        System.out.printf("%-5s %-30s\n", "ID", "Tên Phòng Ban");
        for (Department d : list) {
            System.out.printf("%-5d %-30s\n", d.getDepartmentId(), d.getDepartmentName());
        }
    }

    private void findById() {
        System.out.print("Nhập ID phòng ban cần tìm: ");
        int id = Integer.parseInt(scanner.nextLine());
        Department department = departmentController.findById(id);
        if (department != null) {
            System.out.println("\n--- THÔNG TIN PHÒNG BAN ---");
            System.out.println("ID: " + department.getDepartmentId());
            System.out.println("Tên: " + department.getDepartmentName());
        } else {
            System.out.println("❌ Không tìm thấy phòng ban có ID = " + id);
        }
    }

    private void update() {
        int id;
        while (true) {
            System.out.print("Nhập ID phòng ban cần sửa: ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    System.out.println("❌ ID phải lớn hơn 0!");
                    continue;
                }
                if (departmentController.findById(id) == null) {
                    System.out.println("❌ ID không tồn tại trong hệ thống!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ ID phải là một số nguyên!");
            }
        }

        String name;
        while (true) {
            System.out.print("Nhập tên mới: ");
            name = scanner.nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.out.println("❌ Tên không được để trống!");
                continue;
            }
            if (departmentController.checkIfNameExists(name)) {
                System.out.println("❌ Tên phòng ban đã tồn tại!");
                continue;
            }
            break;
        }

        if (departmentController.update(id, name)) {
            System.out.println("✅ Cập nhật thành công!");
        } else {
            System.out.println("❌ Cập nhật thất bại!");
        }
    }

    private void delete() {
        int id;
        while (true) {
            System.out.print("Nhập ID phòng ban cần xóa: ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    System.out.println("❌ ID phải lớn hơn 0!");
                    continue;
                }
                if (departmentController.findById(id) == null) {
                    System.out.println("❌ ID không tồn tại trong hệ thống!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ ID phải là một số nguyên!");
            }
        }

        if (departmentController.delete(id)) {
            System.out.println("✅ Xóa thành công!");
        } else {
            System.out.println("❌ Xóa thất bại (ID không tồn tại hoặc phòng ban đang có nhân viên)!");
        }
    }

//
    private void create() {
        String name;
        while (true) {
            System.out.print("Nhập tên phòng ban mới: ");
            name = scanner.nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.out.println("❌ Tên không được để trống!");
                continue;
            }
            if (departmentController.checkIfNameExists(name)) {
                System.out.println("❌ Tên phòng ban đã tồn tại!");
                continue;
            }
            break;
        }

        if (departmentController.create(name)) {
            System.out.println("✅ Thêm thành công!");
        } else {
            System.out.println("❌ Thêm thất bại!");
        }
    }

    private void displayMostEmployees() {
        List<Department> list = departmentController.findMostEmployees();
        System.out.println("\n========== PHÒNG BAN CÓ NHIỀU NHÂN VIÊN NHẤT ==========");
        System.out.printf("%-5s %-30s\n", "ID", "Tên Phòng Ban");
        for (Department d : list) {
            System.out.printf("%-5d %-30s\n", d.getDepartmentId(), d.getDepartmentName());
        }
    }

    private void displayLeastEmployees() {
        List<Department> list = departmentController.findLeastEmployees();
        System.out.println("\n========== PHÒNG BAN CÓ ÍT NHÂN VIÊN NHẤT ==========");
        System.out.printf("%-5s %-30s\n", "ID", "Tên Phòng Ban");
        for (Department d : list) {
            System.out.printf("%-5d %-30s\n", d.getDepartmentId(), d.getDepartmentName());
        }
    }

}

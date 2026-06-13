package com.zalo.auto.frontend;

import com.zalo.auto.backend.controller.PositionController;
import com.zalo.auto.entity.Position;
import com.zalo.auto.enums.PositionName;

import java.util.List;
import java.util.Scanner;

public class PositionFunction {
    private PositionController positionController;
    private Scanner scanner;

    public PositionFunction() {
        positionController = new PositionController();
        scanner = new Scanner(System.in);
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ CHỨC VỤ ---");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật (theo ID)");
            System.out.println("4. Xóa (theo ID)");
            System.out.println("5. Chức vụ nhiều nhân viên nhất");
            System.out.println("6. Chức vụ ít nhân viên nhất");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn chức năng: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: displayAll(); break;
                    case 2: create(); break;
                    case 3: update(); break;
                    case 4: delete(); break;
                    case 5: displayMostEmployees(); break;
                    case 6: displayLeastEmployees(); break;
                    case 0: return;
                    default: System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }

    private void displayAll() {
        List<Position> list = positionController.findAll();
        System.out.println("\n========== DANH SÁCH CHỨC VỤ ==========");
        System.out.printf("%-5s %-30s\n", "ID", "Tên Chức Vụ");
        for (Position p : list) {
            System.out.printf("%-5d %-30s\n", p.getPositionId(), p.getPositionName());
        }
    }

    private void create() {
        PositionName name;
        while (true) {
            name = inputPositionName();
            if (positionController.checkExistName(name)) {
                System.out.println("❌ Tên chức vụ đã tồn tại trong hệ thống!");
                continue;
            }
            break;
        }

        if (positionController.create(name)) {
            System.out.println("✅ Thêm thành công!");
        } else {
            System.out.println("❌ Thêm thất bại!");
        }
    }

    private void update() {
        int id;
        while (true) {
            try {
                System.out.print("Nhập ID chức vụ cần sửa: ");
                id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    System.out.println("❌ ID phải lớn hơn 0!");
                    continue;
                }
                if (positionController.findById(id) == null) {
                    System.out.println("❌ ID không tồn tại trong hệ thống!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID phải là số nguyên!");
            }
        }

        PositionName name;
        while (true) {
            name = inputPositionName();
            if (positionController.checkExistName(name)) {
                System.out.println("❌ Tên chức vụ đã tồn tại trong hệ thống!");
                continue;
            }
            break;
        }

        if (positionController.update(id, name)) {
            System.out.println("✅ Cập nhật thành công!");
        } else {
            System.out.println("❌ Không tìm thấy ID hoặc cập nhật thất bại!");
        }
    }

    private void delete() {
        int id;
        while (true) {
            try {
                System.out.print("Nhập ID chức vụ cần xóa: ");
                id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    System.out.println("❌ ID phải lớn hơn 0!");
                    continue;
                }
                if (positionController.findById(id) == null) {
                    System.out.println("❌ ID không tồn tại trong hệ thống!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: ID phải là số nguyên!");
            }
        }

        if (positionController.delete(id)) {
            System.out.println("✅ Xóa thành công!");
        } else {
            System.out.println("❌ Xóa thất bại (ID không tồn tại hoặc đang có nhân viên)!");
        }
    }

    private void displayMostEmployees() {
        List<Position> list = positionController.findMostEmployees();
        System.out.println("\n--- CHỨC VỤ NHIỀU NHÂN VIÊN NHẤT ---");
        for (Position p : list) System.out.println("- " + p.getPositionName());
    }

    private void displayLeastEmployees() {
        List<Position> list = positionController.findLeastEmployees();
        System.out.println("\n--- CHỨC VỤ ÍT NHÂN VIÊN NHẤT ---");
        for (Position p : list) System.out.println("- " + p.getPositionName());
    }

    private PositionName inputPositionName() {
        while (true) {
            System.out.println("Chọn chức vụ: 1.Dev, 2.Test, 3.Scrum_Master, 4.PM");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: return PositionName.Dev;
                    case 2: return PositionName.Test;
                    case 3: return PositionName.Scrum_Master;
                    case 4: return PositionName.PM;
                    default: System.out.println("Vui lòng chọn từ 1 đến 4!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập số!");
            }
        }
    }
}

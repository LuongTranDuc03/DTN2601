package frontend;

import backend.QLPosition;
import entity.Position;
import entity.PositionName;

import java.util.List;
import java.util.Scanner;

public class PositionFunction {
    private QLPosition qlPosition;
    private Scanner scanner;

    public PositionFunction() {
        qlPosition = new QLPosition();
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
        }
    }

    private void displayAll() {
        List<Position> list = qlPosition.getAllPositions();
        System.out.println("\n========== DANH SÁCH CHỨC VỤ ==========");
        System.out.printf("%-5s %-30s\n", "ID", "Tên Chức Vụ");
        for (Position p : list) {
            System.out.printf("%-5d %-30s\n", p.getPositionId(), p.getPositionName());
        }
    }

    private void create() {
        PositionName name = inputPositionName();
        if (qlPosition.createPosition(name)) {
            System.out.println("✅ Thêm thành công!");
        }
    }

    private void update() {
        System.out.print("Nhập ID chức vụ cần sửa: ");
        int id = Integer.parseInt(scanner.nextLine());
        PositionName name = inputPositionName();
        if (qlPosition.updatePosition(id, name)) {
            System.out.println("✅ Cập nhật thành công!");
        } else {
            System.out.println("❌ Không tìm thấy ID!");
        }
    }

    private void delete() {
        System.out.print("Nhập ID chức vụ cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (qlPosition.deletePosition(id)) {
            System.out.println("✅ Xóa thành công!");
        } else {
            System.out.println("❌ Xóa thất bại!");
        }
    }

    private void displayMostEmployees() {
        List<Position> list = qlPosition.getPositionsWithMostEmployees();
        System.out.println("\n--- CHỨC VỤ NHIỀU NHÂN VIÊN NHẤT ---");
        for (Position p : list) System.out.println(p.getPositionName());
    }

    private void displayLeastEmployees() {
        List<Position> list = qlPosition.getPositionsWithLeastEmployees();
        System.out.println("\n--- CHỨC VỤ ÍT NHÂN VIÊN NHẤT ---");
        for (Position p : list) System.out.println(p.getPositionName());
    }

    private PositionName inputPositionName() {
        while (true) {
            System.out.println("Chọn chức vụ: 1.Dev, 2.Test, 3.Scrum_Master, 4.PM");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: return PositionName.Dev;
                case 2: return PositionName.Test;
                case 3: return PositionName.Scrum_Master;
                case 4: return PositionName.PM;
                default: System.out.println("Chọn lại!");
            }
        }
    }
}

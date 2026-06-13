package frontend;

import backend.QLDepartment;
import entity.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentFunction {
    private QLDepartment qlDepartment;
    private Scanner scanner;

    public DepartmentFunction() {
        qlDepartment = new QLDepartment();
        scanner = new Scanner(System.in);
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ PHÒNG BAN ---");
            System.out.println("1. Hiển thị danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật (theo ID)");
            System.out.println("4. Xóa (theo ID)");
            System.out.println("5. Phòng ban nhiều nhân viên nhất");
            System.out.println("6. Phòng ban ít nhân viên nhất");
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
        List<Department> list = qlDepartment.getAllDepartments();
        System.out.println("\n========== DANH SÁCH PHÒNG BAN ==========");
        System.out.printf("%-5s %-30s\n", "ID", "Tên Phòng Ban");
        for (Department d : list) {
            System.out.printf("%-5d %-30s\n", d.getDepartmentId(), d.getDepartmentName());
        }
    }

    private void create() {
        System.out.print("Nhập tên phòng ban mới: ");
        String name = scanner.nextLine();
        if (qlDepartment.createDepartment(name)) {
            System.out.println("✅ Thêm thành công!");
        } else {
            System.out.println("❌ Thêm thất bại!");
        }
    }

    private void update() {
        System.out.print("Nhập ID phòng ban cần sửa: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập tên mới: ");
        String name = scanner.nextLine();
        if (qlDepartment.updateDepartment(id, name)) {
            System.out.println("✅ Cập nhật thành công!");
        } else {
            System.out.println("❌ Không tìm thấy ID!");
        }
    }

    private void delete() {
        System.out.print("Nhập ID phòng ban cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (qlDepartment.deleteDepartment(id)) {
            System.out.println("✅ Xóa thành công!");
        } else {
            System.out.println("❌ Xóa thất bại!");
        }
    }

    private void displayMostEmployees() {
        List<Department> list = qlDepartment.getDepartmentsWithMostEmployees();
        System.out.println("\n--- PHÒNG BAN NHIỀU NHÂN VIÊN NHẤT ---");
        for (Department d : list) System.out.println(d.getDepartmentName());
    }

    private void displayLeastEmployees() {
        List<Department> list = qlDepartment.getDepartmentsWithLeastEmployees();
        System.out.println("\n--- PHÒNG BAN ÍT NHÂN VIÊN NHẤT ---");
        for (Department d : list) System.out.println(d.getDepartmentName());
    }
}

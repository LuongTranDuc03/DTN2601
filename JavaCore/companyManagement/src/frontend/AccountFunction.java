package frontend;

import backend.QLAccount;
import entity.Account;

import java.util.List;
import java.util.Scanner;

public class AccountFunction {
    private QLAccount qlAccount;
    private Scanner scanner;

    public AccountFunction() {
        qlAccount = new QLAccount();
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
        }
    }

    private void displayAll() {
        List<Account> list = qlAccount.getAllAccounts();
        System.out.println("\n==================================== DANH SÁCH NHÂN VIÊN ====================================");
        System.out.printf("%-5s %-25s %-20s %-15s %-15s %-12s\n", "ID", "Email", "Họ Tên", "Phòng Ban", "Chức Vụ", "Ngày Tạo");
        for (Account a : list) {
            System.out.printf("%-5d %-25s %-20s %-15s %-15s %-12s\n",
                a.getAccountId(), a.getEmail(), a.getFullName(),
                a.getDepartment().getDepartmentName(), a.getPosition().getPositionName(),
                a.getCreateDate());
        }
    }

    private void create() {
        System.out.print("Nhập Email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập Họ Tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập ID Phòng Ban: ");
        int dId = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập ID Chức Vụ: ");
        int pId = Integer.parseInt(scanner.nextLine());

        if (qlAccount.createAccount(email, name, dId, pId)) {
            System.out.println("✅ Thêm thành công!");
        } else {
            System.out.println("❌ Thêm thất bại!");
        }
    }

    private void update() {
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

        if (qlAccount.updateAccount(id, email, name, dId, pId)) {
            System.out.println("✅ Cập nhật thành công!");
        } else {
            System.out.println("❌ Cập nhật thất bại!");
        }
    }

    private void delete() {
        System.out.print("Nhập ID nhân viên cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (qlAccount.deleteAccount(id)) {
            System.out.println("✅ Xóa thành công!");
        } else {
            System.out.println("❌ Xóa thất bại!");
        }
    }

    private void searchById() {
        System.out.print("Nhập ID nhân viên cần tìm: ");
        int id = Integer.parseInt(scanner.nextLine());
        Account a = qlAccount.getAccountById(id);
        if (a != null) {
            System.out.println("Kết quả: " + a);
        } else {
            System.out.println("❌ Không tìm thấy!");
        }
    }
}

package frontend;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountFunction accountFunc = new AccountFunction();
        DepartmentFunction deptFunc = new DepartmentFunction();
        PositionFunction posFunc = new PositionFunction();

        while (true) {
            System.out.println("\n********** HỆ THỐNG QUẢN LÝ CÔNG TY **********");
            System.out.println("1. Quản lý Nhân Viên (Account)");
            System.out.println("2. Quản lý Phòng Ban (Department)");
            System.out.println("3. Quản lý Chức Vụ (Position)");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn đối tượng quản lý: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: accountFunc.menu(); break;
                    case 2: deptFunc.menu(); break;
                    case 3: posFunc.menu(); break;
                    case 0:
                        System.out.println("Đang thoát...");
                        System.exit(0);
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên!");
            }
        }
    }
}

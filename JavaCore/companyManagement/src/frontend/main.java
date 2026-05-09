package frontend;

import backend.QLAccount;
import backend.QLDepartment;
import backend.QLPosition;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QLDepartment qlDepartment = new QLDepartment();
        QLPosition qlPosition = new QLPosition();
        QLAccount qlAccount = new QLAccount();

        int choice;
        boolean running = true;

        while (running) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║      QUẢN LÝ CÔNG TY - MENU CHÍNH      ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║  1. Xem danh sách Phòng Ban            ║");
            System.out.println("║  2. Xem danh sách Chức Vụ              ║");
            System.out.println("║  3. Xem danh sách Nhân Viên            ║");
            System.out.println("║  0. Thoát chương trình                 ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        qlDepartment.displayAllDepartments();
                        break;
                    case 2:
                        qlPosition.displayAllPositions();
                        break;
                    case 3:
                        qlAccount.displayAllAccounts();
                        break;
                    case 0:
                        System.out.println("\nCảm ơn bạn đã sử dụng chương trình. Tạm biệt!");
                        running = false;
                        break;
                    default:
                        System.out.println("\n❌ Lựa chọn không hợp lệ! Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Vui lòng nhập một số nguyên!");
            } catch (Exception e) {
                System.err.println("\n❌ Lỗi: " + e.getMessage());
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}

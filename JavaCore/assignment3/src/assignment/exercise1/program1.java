package assignment.exercise1;

import java.util.Scanner;

public class program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        datatypeCasting exercise = new datatypeCasting();
        int choice;

        do {
            System.out.println("\n---------- MENU ----------");
            System.out.println("1. Question 1 (Casting lương)");
            System.out.println("2. Question 2 (Random 5 chữ số)");
            System.out.println("3. Question 3 (Lấy 2 số cuối)");
            System.out.println("4. Question 4 (Chia 2 số nguyên)");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    exercise.question1();
                    break;
                case 2:
                    exercise.question2();
                    break;
                case 3:
                    exercise.question3();
                    break;
                case 4:
                    System.out.print("Nhập số a: ");
                    int a = sc.nextInt();
                    System.out.print("Nhập số b: ");
                    int b = sc.nextInt();
                    if (b == 0) {
                        System.out.println("Lỗi: Không thể chia cho 0!");
                    } else {
                        System.out.println("Kết quả a/b: " + exercise.question4(a, b));
                    }
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        } while (choice != 0);

        sc.close();
    }
}
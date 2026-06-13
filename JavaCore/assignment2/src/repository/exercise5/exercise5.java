package repository.exercise5;

import entity.Account;
import entity.Department;
import entity.Position;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class exercise5 {
    Scanner sc = new Scanner(System.in);

    public void question1() {
        System.out.println("--- Question 1 ---");
        System.out.println("Nhập vào 3 số nguyên");

        try {
            int a, b, c;
            System.out.print("a: ");
            a = Integer.parseInt(sc.nextLine());
            System.out.print("b: ");
            b = Integer.parseInt(sc.nextLine());
            System.out.print("c: ");
            c = Integer.parseInt(sc.nextLine());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void question2() {
        System.out.println("--- Question 2 ---");
        System.out.println("Nhập vào 2 số thực");

        try {
            int a, b;
            System.out.print("a: ");
            a = Integer.parseInt(sc.nextLine());
            System.out.print("b: ");
            b = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void question3() {
        System.out.println("--- Question 3 ---");
        System.out.print("Nhập họ và tên: ");
        try {
            String fullname;
            fullname = sc.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void question4() {
        System.out.println("--- Question 4 ---");
        System.out.println("Nhập vào ngày sinh nhật (DD/MM/YYY): ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            String input = sc.nextLine();
            LocalDate d = LocalDate.parse(input, formatter);
            System.out.println("Ngày bạn đã nhập: " + d.format(formatter));
        } catch (DateTimeParseException e) {
            System.out.println("Lỗi: Định dạng ngày không hợp lệ. Vui lòng nhập đúng dd/MM/yyyy.");
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    public void question5() {
        System.out.println("--- Question 5 ---");
        Account account = new Account();
        Position.PositionName positionName;

        System.out.println("Nhâp thông tin account");
        System.out.println("Id: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Full name: ");
        String fullName = sc.nextLine();
        System.out.println("Position: ");
        System.out.println("1 - Dev");
        System.out.println("2 - Test");
        System.out.println("3 - Scrum Master");
        System.out.println("4 - Project Manager");
        System.out.print("Nhâp số (1-4) ứng với phòn ban: ");

        switch (sc.nextLine()) {
            case "1":
                positionName = Position.PositionName.DEV;
                break;
            case "2":
                positionName = Position.PositionName.TEST;
                break;
            case "3":
                positionName = Position.PositionName.SCRUM_MASTER;
                break;
            case "4":
                positionName = Position.PositionName.PM;
                break;
            default:
                System.out.println("Chưa có phòng ban.");
        }
    }

    public void question6() {
        System.out.println("--- Question 6 ---");
        System.out.print("Nhập id của department: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nập tên của department: ");
        String departmentName = sc.nextLine();

        Department de = new Department(id, departmentName);
        System.out.println("Tạo thành công");
        System.out.println("ID: " + de.getId());
        System.out.println("Name: " + de.getName());
    }

    public void question7() {
        System.out.println("--- Question 7 ---");

        while (true) {
            System.out.print("Nhập số chẵn: ");
            int id = Integer.parseInt(sc.nextLine());
            if (id % 2 == 0)
                break;
        }
    }

    public void question8() {
        System.out.println("--- Question 8 ---");
        while(true) {
            System.out.println(" 1. Tạo account");
            System.out.println(" 2. Tạo department");
            System.out.println(" 0. Thoát");
            System.out.print("Xin mời nhập chức năng: ");
            switch (Integer.parseInt(sc.nextLine())) {
                case 1:
                    System.out.println("Tạo account");
                    break;
                case 2:
                    System.out.println("Tạo department");
                    question6();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Mời nhập lại.");
            }
        }
    }
}
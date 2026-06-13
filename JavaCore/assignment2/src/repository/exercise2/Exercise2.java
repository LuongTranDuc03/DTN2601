package repository.exercise2;

import entity.Account;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Exercise2 {

    // Question 1: In số nguyên 5
    public void question1() {
        System.out.println("--- Question 1 ---");
        int i = 5;
        System.out.printf("%d%n", i);
    }

    // Question 2: In số 100,000,000 (định dạng có dấu phẩy phân cách)
    public void question2() {
        System.out.println("--- Question 2 ---");
        int i = 100000000;
        System.out.printf(Locale.US, "%,d%n", i);
    }

    // Question 3: In số thực lấy 4 chữ số thập phân
    public void question3() {
        System.out.println("--- Question 3 ---");
        float f = 5.567098f;
        System.out.printf("%.4f%n", f);
    }

    // Question 4: In tên Account "Nguyen Van A" và "dang lam viec"
    public void question4(Account acc) {
        System.out.println("--- Question 4 ---");
        String name = acc.getFullName();
        System.out.printf("Họ và tên: \"%s\" và đang làm việc.%n", name);
    }

    // Question 5: In thời gian hiện tại theo định dạng dd/MM/yyyy HHh:mmp:sss
    public void question5() {
        System.out.println("--- Question 5 ---");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH'h':mm'p':ss's'");
        System.out.println(now.format(formatter));
    }

    // Question 6: In thông tin Account (ID, Email, FullName, DepartmentName) dùng printf
    public void question6(Account[] accounts) {
        System.out.println("--- Question 6 ---");
        // In tiêu đề bảng cho đẹp
        System.out.printf("%-5s | %-20s | %-20s | %-15s%n", "ID", "Email", "Full Name", "Department");
        System.out.println("-------------------------------------------------------------------------");
        for (Account acc : accounts) {
            System.out.printf("%-5d | %-20s | %-20s | %-15s%n",
                    acc.getId(),
                    acc.getEmail(),
                    acc.getFullName(),
                    (acc.getDepartment() != null ? acc.getDepartment().getName() : "N/A"));
        }
    }
}
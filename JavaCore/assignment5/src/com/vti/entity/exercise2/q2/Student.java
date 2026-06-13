package com.vti.entity.exercise2.q2;

import java.util.Scanner;

public class Student extends Person {
    private String studentId;
    private float avgScore;
    private String email;

    public Student() {
        super();
    }

    @Override
    public void inputInfo() {
        super.inputInfo();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma sinh vien: ");
        this.studentId = scanner.nextLine();
        System.out.print("Nhap diem trung binh: ");
        this.avgScore = Float.parseFloat(scanner.nextLine());
        System.out.print("Nhap email: ");
        this.email = scanner.nextLine();
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Ma sinh vien: " + studentId);
        System.out.println("Diem trung binh: " + avgScore);
        System.out.println("Email: " + email);
    }

    public boolean hasScholarship() {
        return avgScore >= 8.0;
    }
}

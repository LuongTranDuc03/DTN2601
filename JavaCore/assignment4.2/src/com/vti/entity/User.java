package com.vti.entity;

import java.util.Scanner;

public abstract class User {
    private String name;
    private double salaryRatio;

    public User() {
    }

    public User(String name, double salaryRatio) {
        this.name = name;
        this.salaryRatio = salaryRatio;
    }

    public void inputInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên: ");
        this.name = scanner.nextLine();
        System.out.print("Nhập hệ số lương: ");
        this.salaryRatio = scanner.nextDouble();
    }

    public String getName() {
        return name;
    }

    public double getSalaryRatio() {
        return salaryRatio;
    }

    public abstract double calculatePay();

    public void displayInfor() {
        System.out.println("Tên: " + name);
        System.out.println("Hệ số lương: " + salaryRatio);
        System.out.println("Lương thực nhận: " + calculatePay());
    }
}

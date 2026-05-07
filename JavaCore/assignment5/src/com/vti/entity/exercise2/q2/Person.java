package com.vti.entity.exercise2.q2;

import java.util.Scanner;

public class Person {
    protected String name;
    protected String gender;
    protected String birthday;
    protected String address;

    public Person() {}

    public Person(String name, String gender, String birthday, String address) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public void inputInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ten: ");
        this.name = scanner.nextLine();
        System.out.print("Nhap gioi tinh: ");
        this.gender = scanner.nextLine();
        System.out.print("Nhap ngay sinh: ");
        this.birthday = scanner.nextLine();
        System.out.print("Nhap dia chi: ");
        this.address = scanner.nextLine();
    }

    public void showInfo() {
        System.out.println("Ten: " + name);
        System.out.println("Gioi tinh: " + gender);
        System.out.println("Ngay sinh: " + birthday);
        System.out.println("Dia chi: " + address);
    }
}

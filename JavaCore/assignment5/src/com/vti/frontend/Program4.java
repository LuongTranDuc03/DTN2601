package com.vti.frontend;

import com.vti.backend.exercise2.q2.StudentManagement;
import java.util.Scanner;

public class Program4 {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Nhap so luong sinh vien can them:");
        int n = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap thong tin sinh vien thu " + (i + 1) + ":");
            management.addStudent();
        }
        
        System.out.println("\n--- DANH SACH SINH VIEN ---");
        management.showAll();
        
        scanner.close();
    }
}

package com.vti.frontend;

import com.vti.backend.exercise1.TuyenSinh;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        TuyenSinh tuyenSinh = new TuyenSinh();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("========= QUAN LY TUYEN SINH =========");
            System.out.println("1. Them moi thi sinh");
            System.out.println("2. Hien thi thong tin thi sinh");
            System.out.println("3. Tim kien theo so bao danh");
            System.out.println("4. Thoat");
            System.out.print("Chon chuc nang: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
                choice = 0;
                continue;
            }

            switch (choice) {
                case 1:
                    tuyenSinh.addCandidate();
                    break;
                case 2:
                    tuyenSinh.showInfo();
                    break;
                case 3:
                    tuyenSinh.searchBySBD();
                    break;
                case 4:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}

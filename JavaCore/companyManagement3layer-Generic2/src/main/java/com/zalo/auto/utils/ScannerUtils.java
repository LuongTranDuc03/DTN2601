package com.zalo.auto.utils;

import java.util.Scanner;

public class ScannerUtils {
    private static Scanner sc = new Scanner(System.in);

    public static String inputEmail() {
        while (true) {
            String email = sc.nextLine(); // equals(); so sanh gtri, == so sánh địa chỉ
            if (email == null || email.trim().isEmpty() || !email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                System.out.print("Nhập lại: ");
            } else {
                return email;
            }
        }
    }
}

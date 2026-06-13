package com.vti.backend;

import com.vti.entity.Circle;
import com.vti.entity.Account;
import com.vti.entity.Date;

public class Exercise4CircleAccountDate {
    
    // Question 1: Demo Circle
    public void question1() {
        System.out.println("Question 1: Demo Circle Class");
        System.out.println("==========================================");
        
        Circle circle1 = new Circle();
        Circle circle2 = new Circle(2.5);
        Circle circle3 = new Circle(3.0, "blue");
        
        System.out.println("Circle 1 (default): " + circle1);
        System.out.println("Circle 2 (radius=2.5): " + circle2);
        System.out.println("Circle 3 (radius=3.0, color=blue): " + circle3);
        
        circle1.setRadius(1.5);
        circle1.setColor("green");
        System.out.println("\nCircle 1 sau khi thay đổi: " + circle1);
        
        System.out.println("\nDiện tích Circle 2: " + String.format("%.2f", circle2.getArea()));
    }
    
    // Question 2: Demo Account
    public void question2() {
        System.out.println("\n\nQuestion 2: Demo Account Class");
        System.out.println("==========================================");
        
        Account account1 = new Account(1, "john@example.com", "johndoe", "John Doe");
        Account account2 = new Account(2, "jane@example.com", "janedoe", "Jane", "Doe");
        
        System.out.println("Account 1: " + account1);
        System.out.println("Account 2: " + account2);
        
        System.out.println("\nDemo tính năng Account:");
        System.out.println("Initial Balance Account 1: " + account1.getBalance());
        
        account1.credit(500);
        System.out.println("Sau khi credit 500: " + account1.getBalance());
        
        account1.debit(100);
        System.out.println("Sau khi debit 100: " + account1.getBalance());
    }
    
    // Question 3: Demo Date
    public void question3() {
        System.out.println("\n\nQuestion 3: Demo Date Class");
        System.out.println("==========================================");
        
        Date date1 = new Date(15, 3, 2024);
        Date date2 = new Date(29, 2, 2024);
        Date date3 = new Date(31, 12, 2023);
        
        System.out.println("Date 1: " + date1);
        System.out.println("Date 2: " + date2);
        System.out.println("Date 3: " + date3);
        
        System.out.println("\nKiểm tra năm nhuận:");
        System.out.println("Năm 2024 có phải năm nhuận? " + date1.isLeapYear());
        System.out.println("Năm 2023 có phải năm nhuận? " + date3.isLeapYear());
        System.out.println("Năm 2000 có phải năm nhuận? " + new Date(1, 1, 2000).isLeapYear());
        System.out.println("Năm 2100 có phải năm nhuận? " + new Date(1, 1, 2100).isLeapYear());
        
        date1.setDay(25);
        date1.setMonth(12);
        date1.setYear(2025);
        System.out.println("\nDate 1 sau khi thay đổi: " + date1);
    }
}

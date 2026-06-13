package com.vti.backend;

import com.vti.entity.VietnamesePhone;
import com.vti.entity.User;
import com.vti.entity.Employee;
import com.vti.entity.Manager;
import com.vti.entity.Waiter;

public class Exercise6 {
    public void question1() {
        VietnamesePhone vnPhone = new VietnamesePhone();
        
        System.out.println("--- Testing insertContact ---");
        vnPhone.insertContact("Nguyen Van A", "0987654321");
        vnPhone.insertContact("Tran Thi B", "0123456789");
        
        System.out.println("\n--- Testing searchContact ---");
        vnPhone.searchContact("Nguyen Van A");
        
        System.out.println("\n--- Testing updateContact ---");
        vnPhone.updateContact("Nguyen Van A", "0111222333");
        vnPhone.searchContact("Nguyen Van A");
        
        System.out.println("\n--- Testing removeContact ---");
        vnPhone.removeContact("Tran Thi B");
        vnPhone.searchContact("Tran Thi B");
    }

    public void question2() {
        System.out.println("--- Demo Question 2: Abstract User ---");
        
        // Tạo một lớp cụ thể để demo (ví dụ Employee)
        User user = new User() {
            @Override
            public double calculatePay() {
                return getSalaryRatio() * 5000000; // Giả sử lương cơ bản là 5 triệu
            }
        };

        user.inputInfo();
        user.displayInfor();
    }

    public void question3() {
        System.out.println("\n--- Demo Question 3: Employee, Manager, Waiter ---");
        
        User employee = new Employee("Nguyen Van A", 2.0);
        User manager = new Manager("Tran Thi B", 5.0);
        User waiter = new Waiter("Le Van C", 1.5);

        System.out.println("Thông tin Employee:");
        employee.displayInfor();
        
        System.out.println("\nThông tin Manager:");
        manager.displayInfor();
        
        System.out.println("\nThông tin Waiter:");
        waiter.displayInfor();
    }
}

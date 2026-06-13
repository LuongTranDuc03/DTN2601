package com.vti.backend;

import com.vti.entity.*;

public class Exercise3 {
    public void question1() {
        System.out.println("Question 1: Private Access Modifier - Department Demonstration");
        Department dept = new Department(1, "IT");
        
        System.out.println("Department ID (via getter): " + dept.getId());
        System.out.println("Department Name (via getter): " + dept.getName());
        
        dept.setName("Software Development");
        System.out.println("Updated Department Name: " + dept.getName());
    }
    
    public void question2() {
        System.out.println("\nQuestion 2: Private Access Modifier - Account Demonstration");
        Account account = new Account(1, "john@example.com", "johndoe", "John", "Doe");
        

        System.out.println("Account ID: " + account.getId());
        System.out.println("Account Email: " + account.getEmail());
        System.out.println("Account Username: " + account.getUsername());
        System.out.println("Account FullName: " + account.getFullName());
        
        account.setEmail("john.new@example.com");
        System.out.println("Updated Email: " + account.getEmail());
    }
    
    public void question3() {
        System.out.println("\nQuestion 3: Private Access Modifier - Group Demonstration");
        Account creator = new Account(1, "admin@example.com", "admin", "Admin", "User");
        Group group = new Group("Development Team", creator, new String[]{"dev1", "dev2"}, java.time.LocalDate.now());
        
        System.out.println("Group Name: " + group.getName());
        System.out.println("Group Creator: " + group.getCreator());
        System.out.println("Group CreateDate: " + group.getCreateDate());
        
        group.setName("QA Team");
        System.out.println("Updated Group Name: " + group.getName());
    }
}

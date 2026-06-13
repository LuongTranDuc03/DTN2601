package com.vti.backend;

import com.vti.entity.*;
import java.time.LocalDate;

public class Exercise1 {
    
    // Question 1: Test Department Constructors
    public void question1() {
        System.out.println("Question 1: Test Department Constructors");
        Department department1 = new Department(1, "Sale");
        Department department2 = new Department(2, "Marketing");
        Department department0 = new Department("IT");
        
        System.out.println("Department 0: " + department0);
        System.out.println("Department 1: " + department1);
        System.out.println("Department 2: " + department2);
    }
    
    // Question 2: Test Account Constructors
    public void question2() {
        System.out.println("\nQuestion 2: Test Account Constructors");
        Position position1 = new Position(1, Position.PositionName.DEV);
        Position position2 = new Position(2, Position.PositionName.TEST);
        
        Account account1 = new Account(1, "john.doe@example.com", "johndoe", "John Doe");
        Account account2 = new Account(2, "jane.smith@example.com", "janesmith", "Jane", "Smith");
        Account account3 = new Account(3, "bob.wilson@example.com", "bobwilson", "Bob", "Wilson", position1);
        Account account4 = new Account(4, "alice.johnson@example.com", "alicejohnson", "Alice", "Johnson", position2, LocalDate.of(2025, 1, 15));
        
        System.out.println("Account 1: " + account1);
        System.out.println("Account 2: " + account2);
        System.out.println("Account 3: " + account3);
        System.out.println("Account 4: " + account4);
    }
    
    // Question 3: Test Group Constructors
    public void question3() {
        System.out.println("\nQuestion 3: Test Group Constructors");
        Account creator = new Account(1, "john.doe@example.com", "johndoe", "John Doe");
        Account account1 = new Account(2, "jane.smith@example.com", "janesmith", "Jane Smith");
        Account account2 = new Account(3, "bob.wilson@example.com", "bobwilson", "Bob Wilson");
        
        // Constructor a: Không có parameters
        Group group1 = new Group();
        
        // Constructor b: (GroupName, Creator, Account[] accounts, CreateDate)
        Account[] accounts = {account1, account2};
        Group group2 = new Group("Java Freshers", creator, accounts, LocalDate.of(2025, 3, 1));
        
        // Constructor c: (GroupName, Creator, String[] usernames, CreateDate)
        String[] usernames = {"user1", "user2", "user3"};
        Group group3 = new Group("QA Team", creator, usernames, LocalDate.of(2025, 2, 15));
        
        System.out.println("Group 1: " + group1);
        System.out.println("Group 2: " + group2);
        System.out.println("Group 3: " + group3);
    }
}

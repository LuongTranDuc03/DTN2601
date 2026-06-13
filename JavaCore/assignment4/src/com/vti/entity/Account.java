package com.vti.entity;

import java.time.LocalDate;

public class Account {
    private int id;
    private String email;
    private String username;
    private String fullName;
    private int balance = 0;
    private Department department;
    private Position position;
    private LocalDate createDate;

    public Account() {}

    public Account(int id, String email, String username, String fullName) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
    }

    public Account(int id, String email, String username, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.fullName = firstName + lastName;
    }

    public Account(int id, String email, String username, String firstName, String lastName, Position position) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.fullName = firstName + lastName;
        this.position = position;
        this.createDate = LocalDate.now();
    }

    public Account(int id, String email, String username, String firstName, String lastName, Position position, LocalDate createDate) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.fullName = firstName + lastName;
        this.position = position;
        this.createDate = createDate;
    }

    public Account(int id, String email, String username, String fullName, Department department, Position position, LocalDate createDate) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
        this.department = department;
        this.position = position;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    // Method để nạp tiền (credit)
    public void credit(int amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Nạp tiền thành công: +" + amount + ". Số dư: " + this.balance);
        } else {
            System.out.println("Số tiền phải > 0");
        }
    }

    // Method để rút tiền (debit)
    public void debit(int amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Rút tiền thành công: -" + amount + ". Số dư: " + this.balance);
        } else if (amount > this.balance) {
            System.out.println("Số tiền rút vượt quá số dư");
        } else {
            System.out.println("Số tiền phải > 0");
        }
    }

    // Method để chuyển tiền (transfer)
    public void transferTo(Account account, int amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            account.balance += amount;
            System.out.println("Chuyển tiền thành công: -" + amount + " từ " + this.username + " đến " + account.username);
            System.out.println("Số dư " + this.username + ": " + this.balance);
            System.out.println("Số dư " + account.username + ": " + account.balance);
        } else if (amount > this.balance) {
            System.out.println("Số tiền chuyển vượt quá số dư");
        } else {
            System.out.println("Số tiền phải > 0");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", balance=" + balance +
                ", department=" + department +
                ", position=" + position +
                ", createDate=" + createDate +
                '}';
    }
}


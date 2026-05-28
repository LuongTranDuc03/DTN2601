package com.zalo.auto.entity;

import java.time.LocalDate;

public class Account {
    private int accountId;
    private String email;
    private String password;
    private String fullName;
    private Department department;
    private Position position;
    private LocalDate createDate;

    // Constructors
    public Account() {
    }

    public Account(int accountId, String email, String fullName, Department department,
                   Position position, LocalDate createDate) {
        this.accountId = accountId;
        this.email = email;
        this.fullName = fullName;
        this.department = department;
        this.position = position;
        this.createDate = createDate;
    }

    public Account(int accountId, String email, String password, String fullName, Department department, Position position, LocalDate createDate) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.department = department;
        this.position = position;
        this.createDate = createDate;
    }

    public Account(String email, String fullName, Department department,
                   Position position, LocalDate createDate) {
        this.email = email;
        this.fullName = fullName;
        this.department = department;
        this.position = position;
        this.createDate = createDate;
    }

    // Getters and Setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", department=" + department +
                ", position=" + position +
                ", createDate=" + createDate +
                '}';
    }
}

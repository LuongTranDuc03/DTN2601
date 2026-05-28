package com.zalo.auto.dto.csv;

public class AccountCsv {
    private String email;
    private String password;
    private String fullName;
    private String departmentId;
    private String positionId;
    private int lineNumber;

    public AccountCsv(String email, String password, String fullName, String departmentId, String positionId, int lineNumber) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.lineNumber = lineNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}

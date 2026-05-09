package entity;

import java.time.LocalDate;

public class Account {
    private int accountId;
    private String email;
    private String fullName;
    private int departmentId;
    private int positionId;
    private LocalDate createDate;

    // Constructors
    public Account() {
    }

    public Account(int accountId, String email, String fullName, int departmentId, 
                   int positionId, LocalDate createDate) {
        this.accountId = accountId;
        this.email = email;
        this.fullName = fullName;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.createDate = createDate;
    }

    public Account(String email, String fullName, int departmentId, 
                   int positionId, LocalDate createDate) {
        this.email = email;
        this.fullName = fullName;
        this.departmentId = departmentId;
        this.positionId = positionId;
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", departmentId=" + departmentId +
                ", positionId=" + positionId +
                ", createDate=" + createDate +
                '}';
    }
}

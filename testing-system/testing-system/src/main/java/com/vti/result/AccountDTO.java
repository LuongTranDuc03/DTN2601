package com.vti.result;

import com.vti.entity.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
public class AccountDTO {
    private Integer id;
    private String email;
    private String username;
    private String fullName;
    private String department;
    private String position;
    private LocalDate createDate;

    public AccountDTO() {
    }

    public AccountDTO(Integer id, String email, String username, String fullName, String department, String position, LocalDate createDate) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
        this.department = department;
        this.position = position;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getFullName(String fullName) {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public AccountDTO(Account account) {
        if (account != null) {
            this.id = account.getId();
            this.email = account.getEmail();
            this.username = account.getUsername();
            this.fullName = account.getFullName();
            if (account.getDepartment() != null) {
                this.department = account.getDepartment().getName();
            }
            if (account.getPosition() != null && account.getPosition().getPositionName() != null) {
                this.position = account.getPosition().getPositionName().toString();
            }
            if (account.getCreateDate() != null) {
                this.createDate = account.getCreateDate();
            }
        }
    }
}

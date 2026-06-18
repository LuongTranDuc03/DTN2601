package com.vti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "accountid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "fullname", nullable = false, length = 100)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "departmentid", referencedColumnName = "departmentid")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "positionid", referencedColumnName = "positionid")
    private Position position;

    @Column(name = "createdate")
    private LocalDate createDate;

    @Column(name = "password", nullable = false, length = 100)
    private String password = "123456";

    @OneToMany(mappedBy = "creator")
    private List<Group> createdGroups;

    @OneToMany(mappedBy = "account")
    private List<GroupAccount> groupAccounts;
}

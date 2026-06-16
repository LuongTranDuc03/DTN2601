package com.vti.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @Column(name = "department_id") // Anh xa toi truong id cua bang
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tu sinh
    private Integer id;

    @Column(name = "department_name", nullable = false, unique = true, length = 100)
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Account> accounts;
}

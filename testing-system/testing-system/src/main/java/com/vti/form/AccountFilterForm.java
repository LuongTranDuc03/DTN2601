package com.vti.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AccountFilterForm {
    private String username;
    private String email;
    private String fullName;
    private String department;
    private String position;
    private LocalDate createDate;
}

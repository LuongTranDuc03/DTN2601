package com.vti.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateForm {

    @NotBlank(message = "username không được để trống")
    @Length(max = 100, message = "username không được dài quá 100 ký tự")
    private String username;
    private String password;

    @NotBlank(message = "Email không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email không đúng định dạng")
    private String email;
    private String fullName;
    private Integer departmentId;
    private Integer positionId;
    private LocalDate createDate;


}

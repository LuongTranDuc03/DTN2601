package com.vti.form;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCreateForm {

    @NotBlank(message = "Tên phòng ban không được để trống")
    @Length(max = 100, message = "Tên phòng ban không được quá 100 ký tự")
    private String name;
}

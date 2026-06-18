package com.vti.form;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PositionUpdateForm {

    @NotBlank(message = "Tên chức vụ không được để trống")
    private String positionName;
}

package com.vti.result;

import com.vti.entity.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DepartmentDTO {

    private Integer id;
    private String name;

    public DepartmentDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentDTO(Department department) {
        if (department != null) {
            this.id = department.getId();
            this.name = department.getName();
        }
    }
}

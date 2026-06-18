package com.vti.result;

import com.vti.entity.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PositionDTO {

    private Integer id;
    private String positionName;

    public PositionDTO(Integer id, String positionName) {
        this.id = id;
        this.positionName = positionName;
    }

    public PositionDTO(Position position) {
        if (position != null) {
            this.id = position.getId();
            if (position.getPositionName() != null) {
                this.positionName = position.getPositionName().name();
            }
        }
    }
}

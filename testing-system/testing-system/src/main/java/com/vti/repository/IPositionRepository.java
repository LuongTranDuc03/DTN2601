package com.vti.repository;

import com.vti.entity.Position;
import com.vti.enums.PositionName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPositionRepository extends JpaRepository<Position, Integer> {

    Position findByPositionName(PositionName positionName);

    boolean existsByPositionName(PositionName positionName);
}

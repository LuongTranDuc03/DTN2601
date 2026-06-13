package com.zalo.auto.backend.repository;

import com.zalo.auto.entity.Position;
import com.zalo.auto.enums.PositionName;
import java.util.List;

public interface IPositionRepository {
    List<Position> findAll();
    Position findById(int id);
    boolean create(PositionName name);
    boolean update(int id, PositionName name);
    boolean delete(int id);
    List<Position> findMostEmployees();
    List<Position> findLeastEmployees();
}

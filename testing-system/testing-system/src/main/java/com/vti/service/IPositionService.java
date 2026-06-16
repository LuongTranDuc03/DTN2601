package com.vti.service;

import com.vti.entity.Position;

import java.util.List;

public interface IPositionService {

    List<Position> findAll();

    Position findById(Integer id);

    Position findByName(String name);

    Position create(Position position);

    Position update(Position position);

    Position update(Integer id, Position position);

    void delete(Integer id);
}

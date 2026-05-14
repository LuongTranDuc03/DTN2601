package com.zalo.auto.backend.controller;

import com.zalo.auto.backend.service.IPositionService;
import com.zalo.auto.backend.service.impl.PositionServiceImplement;
import com.zalo.auto.entity.Position;
import com.zalo.auto.enums.PositionName;
import java.util.List;

public class PositionController {
    private IPositionService positionService = new PositionServiceImplement();

    public List<Position> findAll() {
        return positionService.findAll();
    }

    public Position findById(int id) {
        return positionService.findById(id);
    }

    public boolean create(PositionName name) {
        return positionService.create(name);
    }

    public boolean update(int id, PositionName name) {
        return positionService.update(id, name);
    }

    public boolean delete(int id) {
        return positionService.delete(id);
    }

    public List<Position> findMostEmployees() {
        return positionService.findMostEmployees();
    }

    public List<Position> findLeastEmployees() {
        return positionService.findLeastEmployees();
    }
}

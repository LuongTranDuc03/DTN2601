package com.zalo.auto.backend.service.impl;

import com.zalo.auto.backend.repository.IPositionRepository;
import com.zalo.auto.backend.repository.impl.PositionRepositoryImplement;
import com.zalo.auto.backend.service.IPositionService;
import com.zalo.auto.entity.Position;
import com.zalo.auto.enums.PositionName;

import java.util.List;

public class PositionServiceImplement implements IPositionService {
    private IPositionRepository positionRepository = new PositionRepositoryImplement();

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position findById(int id) {
        return positionRepository.findById(id);
    }

    @Override
    public boolean create(PositionName name) {
        return positionRepository.create(name);
    }

    @Override
    public boolean update(int id, PositionName name) {
        return positionRepository.update(id, name);
    }

    @Override
    public boolean delete(int id) {
        return positionRepository.delete(id);
    }

    @Override
    public List<Position> findMostEmployees() {
        return positionRepository.findMostEmployees();
    }

    @Override
    public List<Position> findLeastEmployees() {
        return positionRepository.findLeastEmployees();
    }

    @Override
    public boolean checkExistName(PositionName name) {
        return positionRepository.checkExistName(name);
    }
}

package com.vti.service.impl;

import com.vti.entity.Position;
import com.vti.enums.PositionName;
import com.vti.form.PositionCreateForm;
import com.vti.form.PositionUpdateForm;
import com.vti.repository.IPositionRepository;
import com.vti.result.PositionDTO;
import com.vti.service.IPositionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequestMapping
public class PositionServiceImpl implements IPositionService {

    @Autowired
    private IPositionRepository positionRepository;

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Position findById(Integer id) {
        Optional<Position> optional = positionRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Position findByName(String name) {
        return positionRepository.findByPositionName(PositionName.fromValue(name));
    }

    @Override
    public PositionDTO create(PositionCreateForm form) {
        Position position = new Position();
        position.setPositionName(PositionName.fromValue(form.getPositionName()));
        Position saved = positionRepository.save(position);
        return new PositionDTO(saved);
    }

    @Override
    public PositionDTO update(Integer id, PositionUpdateForm form) {
        Position positionUpdate = positionRepository.findById(id).orElse(null);

        if (Objects.isNull(positionUpdate)) {
            throw new RuntimeException("ID khong ton tai");
        }

        positionUpdate.setPositionName(PositionName.fromValue(form.getPositionName()));
        Position saved = positionRepository.save(positionUpdate);
        return new PositionDTO(saved);
    }


    @Override
    public void delete(Integer id) {
        if (!positionRepository.existsById(id)) {
            throw new RuntimeException("ID khong ton tai");
        }
        positionRepository.deleteById(id);
    }
}

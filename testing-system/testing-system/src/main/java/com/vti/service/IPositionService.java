package com.vti.service;

import com.vti.entity.Position;
import com.vti.form.PositionCreateForm;
import com.vti.form.PositionUpdateForm;
import com.vti.result.PositionDTO;

import java.util.List;

public interface IPositionService {

    List<Position> findAll();

    Position findById(Integer id);

    Position findByName(String name);

    PositionDTO create(PositionCreateForm form);

    PositionDTO update(Integer id, PositionUpdateForm form);

    void delete(Integer id);
}

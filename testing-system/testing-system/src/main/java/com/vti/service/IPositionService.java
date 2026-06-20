package com.vti.service;

import com.vti.entity.Position;
import com.vti.form.PositionCreateForm;
import com.vti.form.PositionUpdateForm;
import com.vti.result.PositionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPositionService {

    Page<PositionDTO> findAll(Pageable pageable);

    Position findById(Integer id);

    Position findByName(String name);

    PositionDTO create(PositionCreateForm form);

    PositionDTO update(Integer id, PositionUpdateForm form);

    void delete(Integer id);
}

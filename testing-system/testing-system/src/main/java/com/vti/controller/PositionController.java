package com.vti.controller;

import com.vti.entity.Position;
import com.vti.form.PositionCreateForm;
import com.vti.form.PositionUpdateForm;
import com.vti.result.PositionDTO;
import com.vti.service.IPositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @GetMapping
    public ResponseEntity<List<Position>> findAll() {
        List<Position> positions = positionService.findAll();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Position> findById(@PathVariable(name = "id") Integer id) {
        Position position = positionService.findById(id);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<Position> findByName(@RequestParam(name = "name") String name) {
        Position position = positionService.findByName(name);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PositionDTO> create(@Valid @RequestBody PositionCreateForm form) {
        PositionDTO createdPosition = positionService.create(form);
        return new ResponseEntity<>(createdPosition, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PositionDTO> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody PositionUpdateForm form) {
        PositionDTO updatedPosition = positionService.update(id, form);
        return new ResponseEntity<>(updatedPosition, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        positionService.delete(id);
        return new ResponseEntity<>("Delete success!", HttpStatus.OK);
    }
}

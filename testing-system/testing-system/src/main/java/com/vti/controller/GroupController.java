package com.vti.controller;

import com.vti.entity.Group;
import com.vti.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @GetMapping
    public ResponseEntity<List<Group>> findAll() {
        List<Group> groups = groupService.findAll();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Group> findById(@PathVariable(name = "id") Integer id) {
        Group group = groupService.findById(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<Group> findByName(@RequestParam(name = "name") String name) {
        Group group = groupService.findByName(name);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Group group) {
        Group createdGroup = groupService.create(group);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody Group group) {
        Group updatedGroup = groupService.update(id, group);
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        groupService.delete(id);
        return new ResponseEntity<>("Delete success!", HttpStatus.OK);
    }
}

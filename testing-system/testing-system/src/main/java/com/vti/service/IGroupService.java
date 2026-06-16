package com.vti.service;

import com.vti.entity.Group;

import java.util.List;

public interface IGroupService {

    List<Group> findAll();

    Group findById(Integer id);

    Group findByName(String name);

    Group create(Group group);

    Group update(Group group);

    Group update(Integer id, Group group);

    void delete(Integer id);
}

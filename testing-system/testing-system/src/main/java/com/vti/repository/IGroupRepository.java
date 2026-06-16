package com.vti.repository;

import com.vti.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupRepository extends JpaRepository<Group, Integer> {

    Group findByName(String name);

    boolean existsByName(String name);
}

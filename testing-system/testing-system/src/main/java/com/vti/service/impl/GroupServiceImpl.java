package com.vti.service.impl;

import com.vti.entity.Group;
import com.vti.repository.IGroupRepository;
import com.vti.service.IGroupService;
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
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group findById(Integer id) {
        Optional<Group> optional = groupRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Group findByName(String name) {
        return groupRepository.findByName(name);
    }

    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group update(Group group) {
        if (group.getId() == null || !groupRepository.existsById(group.getId())) {
            throw new RuntimeException("ID khong ton tai");
        }
        return groupRepository.save(group);
    }

    @Override
    public Group update(Integer id, Group group) {
        Group groupUpdate = groupRepository.findById(id).orElse(null);

        if (Objects.isNull(groupUpdate)) {
            throw new RuntimeException("ID khong ton tai");
        }

        groupUpdate.setName(group.getName());
        groupUpdate.setCreator(group.getCreator());
        groupUpdate.setCreateDate(group.getCreateDate());
        return groupRepository.save(groupUpdate);
    }

    @Override
    public void delete(Integer id) {
        if (!groupRepository.existsById(id)) {
            throw new RuntimeException("ID khong ton tai");
        }
        groupRepository.deleteById(id);
    }
}

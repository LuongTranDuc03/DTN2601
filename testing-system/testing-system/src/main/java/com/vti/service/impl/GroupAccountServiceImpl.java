package com.vti.service.impl;

import com.vti.entity.GroupAccount;
import com.vti.entity.GroupAccountId;
import com.vti.repository.IGroupAccountRepository;
import com.vti.service.IGroupAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequestMapping
public class GroupAccountServiceImpl implements IGroupAccountService {

    @Autowired
    private IGroupAccountRepository groupAccountRepository;

    @Override
    public List<GroupAccount> findAll() {
        return groupAccountRepository.findAll();
    }

    @Override
    public GroupAccount findById(GroupAccountId id) {
        Optional<GroupAccount> optional = groupAccountRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public GroupAccount create(GroupAccount groupAccount) {
        return groupAccountRepository.save(groupAccount);
    }

    @Override
    public void delete(GroupAccountId id) {
        if (!groupAccountRepository.existsById(id)) {
            throw new RuntimeException("ID khong ton tai");
        }
        groupAccountRepository.deleteById(id);
    }
}

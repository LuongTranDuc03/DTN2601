package com.vti.service;

import com.vti.entity.GroupAccount;
import com.vti.entity.GroupAccountId;

import java.util.List;

public interface IGroupAccountService {

    List<GroupAccount> findAll();

    GroupAccount findById(GroupAccountId id);

    GroupAccount create(GroupAccount groupAccount);

    void delete(GroupAccountId id);
}

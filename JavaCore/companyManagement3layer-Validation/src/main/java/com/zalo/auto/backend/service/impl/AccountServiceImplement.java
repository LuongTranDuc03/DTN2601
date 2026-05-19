package com.zalo.auto.backend.service.impl;

import com.zalo.auto.backend.repository.IAccountRepository;
import com.zalo.auto.backend.repository.impl.AccountRepositoryImplement;
import com.zalo.auto.backend.service.IAccountService;
import com.zalo.auto.entity.Account;

import java.util.List;

public class AccountServiceImplement implements IAccountService {
    private IAccountRepository accountRepository = new AccountRepositoryImplement();

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public boolean create(String email, String username, String fullName, int departmentId, int positionId) {
        return accountRepository.create(email, username, fullName, departmentId, positionId);
    }

    @Override
    public boolean update(int id, String email, String username, String fullName, int departmentId, int positionId) {
        return accountRepository.update(id, email, username, fullName, departmentId, positionId);
    }

    @Override
    public boolean delete(int id) {
        return accountRepository.delete(id);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return accountRepository.checkExistUsername(username);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return accountRepository.checkExistEmail(email);
    }
}

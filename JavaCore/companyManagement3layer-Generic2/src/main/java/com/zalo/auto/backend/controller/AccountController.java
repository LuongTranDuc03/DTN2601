package com.zalo.auto.backend.controller;

import com.zalo.auto.backend.service.IAccountService;
import com.zalo.auto.backend.service.impl.AccountServiceImplement;
import com.zalo.auto.entity.Account;
import java.util.List;

public class AccountController {
    private IAccountService accountService = new AccountServiceImplement();

    public List<Account> findAll() {
        return accountService.findAll();
    }

    public Account findById(int id) {
        return accountService.findById(id);
    }

    public boolean create(String email, String username, String fullName, int departmentId, int positionId) {
        return accountService.create(email, username, fullName, departmentId, positionId);
    }

    public boolean update(int id, String email, String username, String fullName, int departmentId, int positionId) {
        return accountService.update(id, email, username, fullName, departmentId, positionId);
    }

    public boolean delete(int id) {
        return accountService.delete(id);
    }

    public boolean checkExistUsername(String username) {
        return accountService.checkExistUsername(username);
    }

    public boolean checkExistEmail(String email) {
        return accountService.checkExistEmail(email);
    }

    public String importAccountToCSV(String path) {
        return accountService.importAccountToCSV(path);
    }
}

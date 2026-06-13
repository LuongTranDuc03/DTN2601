package com.vti.service;

import com.vti.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();

    Account findById(Integer id);

    Account findByEmail(String email);

    Account create(Account account);

    Account update(Account account);

    Account update(Integer id, Account account);

    void delete(Integer id);
}

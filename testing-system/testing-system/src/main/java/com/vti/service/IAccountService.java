package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountUpdateForm;
import com.vti.result.AccountDTO;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> findAll();

    AccountDTO findById(Integer id);

    AccountDTO findByEmail(String email);

    Account create(AccountCreateForm form);

    AccountDTO update(Integer id, AccountUpdateForm form);

    void delete(Integer id);
}

package com.vti.service.impl;

import com.vti.entity.Account;
import com.vti.repository.IAccountRepository;
import com.vti.service.IAccountService;
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
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Integer id) {
        Optional<Account> optional = accountRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        if (account.getId() == null || !accountRepository.existsById(account.getId())) {
            throw new RuntimeException("ID khong ton tai");
        }
        return accountRepository.save(account);
    }

    @Override
    public Account update(Integer id, Account account) {
        Account accountUpdate = accountRepository.findById(id).orElse(null);

        if (Objects.isNull(accountUpdate)) {
            throw new RuntimeException("ID khong ton tai");
        }

        accountUpdate.setEmail(account.getEmail());
        accountUpdate.setFullName(account.getFullName());
        accountUpdate.setDepartmentId(account.getDepartmentId());
        accountUpdate.setPositionId(account.getPositionId());
        accountUpdate.setCreateDate(account.getCreateDate());
        accountUpdate.setPassword(account.getPassword());
        return accountRepository.save(accountUpdate);
    }

    @Override
    public void delete(Integer id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("ID khong ton tai");
        }
        accountRepository.deleteById(id);
    }
}

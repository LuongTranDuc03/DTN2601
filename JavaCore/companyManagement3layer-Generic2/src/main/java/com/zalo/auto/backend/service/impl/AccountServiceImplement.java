package com.zalo.auto.backend.service.impl;

import com.zalo.auto.backend.repository.IAccountRepository;
import com.zalo.auto.backend.repository.IDepartmentRepository;
import com.zalo.auto.backend.repository.IPositionRepository;
import com.zalo.auto.backend.repository.impl.AccountRepositoryImplement;
import com.zalo.auto.backend.repository.impl.DepartmentRepositoryImplement;
import com.zalo.auto.backend.repository.impl.PositionRepositoryImplement;
import com.zalo.auto.backend.service.IAccountService;
import com.zalo.auto.backend.service.csv.AccountCsvImport;
import com.zalo.auto.dto.context.AccountContext;
import com.zalo.auto.entity.Account;
import com.zalo.auto.entity.Department;
import com.zalo.auto.entity.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountServiceImplement implements IAccountService {
    private IAccountRepository accountRepository = new AccountRepositoryImplement();
    private IDepartmentRepository departmentRepository = new DepartmentRepositoryImplement();
    private IPositionRepository positionRepository = new PositionRepositoryImplement();

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

    @Override
    public String importAccountToCSV(String path) {
        // Load cache data
        List<Account> existingAccounts = accountRepository.findAll();
        Map<String, Account> mapAccountByEmail = new HashMap<>();
        for (Account a : existingAccounts) {
            if (a.getEmail() != null) {
                mapAccountByEmail.put(a.getEmail().toLowerCase(), a);
            }
        }

        List<Department> departments = departmentRepository.findAll();
        List<Position> positions = positionRepository.findAll();

        AccountContext context = new AccountContext(mapAccountByEmail, departments, positions);
        AccountCsvImport accountCsvImport = new AccountCsvImport();
        return accountCsvImport.importFile(path, context);
    }
}

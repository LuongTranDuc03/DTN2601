package com.vti.service.impl;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountUpdateForm;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.repository.IPositionRepository;
import com.vti.result.AccountDTO;
import com.vti.service.IAccountService;
import com.vti.specification.AccountCustomSpecification;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequestMapping
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private IPositionRepository positionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<AccountDTO> findAll(AccountFilterForm accform, Pageable pageable) {
        Specification<Account> where = null;

        if (accform != null) {
            if (StringUtils.isNotBlank(accform.getUsername())) {
                Specification<Account> spec = new AccountCustomSpecification("username", accform.getUsername());
                where = (where == null) ? spec : where.and(spec);
            }
            if (StringUtils.isNotBlank(accform.getFullName())) {
                Specification<Account> spec = new AccountCustomSpecification("fullName", accform.getFullName());
                where = (where == null) ? spec : where.and(spec);
            }
            if (StringUtils.isNotBlank(accform.getEmail())) {
                Specification<Account> spec = new AccountCustomSpecification("email", accform.getEmail());
                where = (where == null) ? spec : where.and(spec);
            }
            if (StringUtils.isNotBlank(accform.getDepartment())) {
                Specification<Account> spec = new AccountCustomSpecification("department", accform.getDepartment());
                where = (where == null) ? spec : where.and(spec);
            }
            if (StringUtils.isNotBlank(accform.getPosition())) {
                Specification<Account> spec = new AccountCustomSpecification("position", accform.getPosition());
                where = (where == null) ? spec : where.and(spec);
            }
            if (accform.getCreateDate() != null) {
                Specification<Account> spec = new AccountCustomSpecification("createDate", accform.getCreateDate());
                where = (where == null) ? spec : where.and(spec);
            }
        }

        Page<Account> accounts = accountRepository.findAll(where, pageable);

        return accounts.map(acc -> new AccountDTO(acc));
    }

    @Override
    public AccountDTO findById(Integer id) {
        Optional<Account> optional = accountRepository.findById(id);
        Account account = optional.orElse(null);
        return account != null ? new AccountDTO(account) : null;
    }

    @Override
    public AccountDTO findByEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        return account != null ? new AccountDTO(account) : null;
    }

    @Override
    public Account create(AccountCreateForm form) {
        Account account = new Account();
        account.setEmail(form.getEmail());
        account.setUsername(form.getUsername());
        account.setFullName(form.getFullName());
        account.setPassword(form.getPassword());

        Department department = departmentRepository.findById(form.getDepartmentId()).orElse(null);
        if (Objects.isNull(department)) {
            throw new RuntimeException("Department ID not found!");
        }
        account.setDepartment(department);

        Position position = positionRepository.findById(form.getPositionId()).orElse(null);
        if (Objects.isNull(position)) {
            throw new RuntimeException("Position ID not found!");
        }
        account.setPosition(position);

        return accountRepository.save(account);
    }

    @Override
    public AccountDTO update(Integer id, AccountUpdateForm form) {
        Account accountUpdate = accountRepository.findById(id).orElse(null);

        if (Objects.isNull(accountUpdate)) {
            throw new RuntimeException("ID khong ton tai");
        }

        if (form.getEmail() != null) {
            accountUpdate.setEmail(form.getEmail());
        }
        if (form.getFullName() != null) {
            accountUpdate.setFullName(form.getFullName());
        }
        if (form.getPassword() != null) {
            accountUpdate.setPassword(form.getPassword());
        }

        if (form.getDepartmentId() != null) {
            Department department = departmentRepository.findById(form.getDepartmentId()).orElse(null);
            if (Objects.isNull(department)) {
                throw new RuntimeException("Department ID not found!");
            }
            accountUpdate.setDepartment(department);
        }

        if (form.getPositionId() != null) {
            Position position = positionRepository.findById(form.getPositionId()).orElse(null);
            if (Objects.isNull(position)) {
                throw new RuntimeException("Position ID not found!");
            }
            accountUpdate.setPosition(position);
        }

        Account updated = accountRepository.save(accountUpdate);
        return updated != null ? new AccountDTO(updated) : null;
    }

    @Override
    public void delete(Integer id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("ID khong ton tai");
        }
        accountRepository.deleteById(id);
    }
}

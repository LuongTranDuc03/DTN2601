package com.zalo.auto.backend.service;

import com.zalo.auto.entity.Account;
import java.util.List;

public interface IAccountService {
    List<Account> findAll();
    Account findById(int id);
    boolean create(String email, String fullName, int departmentId, int positionId);
    boolean update(int id, String email, String fullName, int departmentId, int positionId);
    boolean delete(int id);
    boolean checkExistEmail(String email);
    String importAccountToCSV(String path);
}

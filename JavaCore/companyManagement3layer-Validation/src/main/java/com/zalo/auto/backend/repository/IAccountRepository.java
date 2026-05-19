package com.zalo.auto.backend.repository;

import com.zalo.auto.entity.Account;
import java.util.List;

public interface IAccountRepository {
    List<Account> findAll();
    Account findById(int id);
    boolean create(String email, String username, String fullName, int departmentId, int positionId);
    boolean update(int id, String email, String username, String fullName, int departmentId, int positionId);
    boolean delete(int id);
    boolean checkExistUsername(String username);
    boolean checkExistEmail(String email);
}

package com.vti.repository;

import com.vti.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

    Account findByEmail(String email);

    boolean existsByEmail(String email);
}

package com.vti.repository;

import com.vti.entity.GroupAccount;
import com.vti.entity.GroupAccountId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupAccountRepository extends JpaRepository<GroupAccount, GroupAccountId> {
}

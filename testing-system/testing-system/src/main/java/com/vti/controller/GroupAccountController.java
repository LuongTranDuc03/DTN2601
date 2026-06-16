package com.vti.controller;

import com.vti.entity.GroupAccount;
import com.vti.entity.GroupAccountId;
import com.vti.service.IGroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/group-accounts")
public class GroupAccountController {

    @Autowired
    private IGroupAccountService groupAccountService;

    @GetMapping
    public ResponseEntity<List<GroupAccount>> findAll() {
        List<GroupAccount> list = groupAccountService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{groupId}/{accountId}")
    public ResponseEntity<GroupAccount> findById(
            @PathVariable(name = "groupId") Integer groupId,
            @PathVariable(name = "accountId") Integer accountId) {
        GroupAccountId id = new GroupAccountId(groupId, accountId);
        GroupAccount groupAccount = groupAccountService.findById(id);
        return new ResponseEntity<>(groupAccount, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody GroupAccount groupAccount) {
        if (groupAccount.getId() == null && groupAccount.getGroup() != null && groupAccount.getAccount() != null) {
            groupAccount.setId(new GroupAccountId(groupAccount.getGroup().getId(), groupAccount.getAccount().getId()));
        }
        GroupAccount created = groupAccountService.create(groupAccount);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{groupId}/{accountId}")
    public ResponseEntity<?> delete(
            @PathVariable(name = "groupId") Integer groupId,
            @PathVariable(name = "accountId") Integer accountId) {
        GroupAccountId id = new GroupAccountId(groupId, accountId);
        groupAccountService.delete(id);
        return new ResponseEntity<>("Delete success!", HttpStatus.OK);
    }
}

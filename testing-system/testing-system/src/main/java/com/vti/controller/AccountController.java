package com.vti.controller;

import com.vti.entity.Account;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        List<Account> accounts = accountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> findById(@PathVariable(name = "id") Integer id) {
        Account account = accountService.findById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping(params = "email")
    public ResponseEntity<Account> findByEmail(@RequestParam(name = "email") String email) {
        Account account = accountService.findByEmail(email);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Account account) {
        Account createdAccount = accountService.create(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody Account account) {
        Account updatedAccount = accountService.update(id, account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        accountService.delete(id);
        return new ResponseEntity<>("Delete success!", HttpStatus.OK);
    }
}

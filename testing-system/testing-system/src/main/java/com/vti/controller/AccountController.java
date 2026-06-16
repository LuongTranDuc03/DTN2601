package com.vti.controller;

import com.vti.entity.Account;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller cho đối tượng Account (Tài khoản).
 * Cung cấp các API Endpoint để thực hiện các thao tác CRUD (Create, Read, Update, Delete).
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    // Tiêm (Inject) dependency IAccountService để sử dụng các phương thức xử lý nghiệp vụ
    @Autowired
    private IAccountService accountService;

    /**
     * Lấy danh sách tất cả các tài khoản.
     * Endpoint: GET /api/accounts
     *
     * @return ResponseEntity chứa danh sách Account và mã trạng thái HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        List<Account> accounts = accountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    /**
     * Lấy thông tin tài khoản theo ID.
     * Endpoint: GET /api/accounts/{id}
     *
     * @param id ID của tài khoản cần tìm (truyền qua Path Variable)
     * @return ResponseEntity chứa thông tin Account tìm được và mã trạng thái HTTP 200 OK
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> findById(@PathVariable(name = "id") Integer id) {
        Account account = accountService.findById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    /**
     * Tìm kiếm tài khoản theo địa chỉ email.
     * Endpoint: GET /api/accounts?email=...
     *
     * @param email Địa chỉ email của tài khoản cần tìm (truyền qua Request Parameter)
     * @return ResponseEntity chứa thông tin Account tìm được và mã trạng thái HTTP 200 OK
     */
    @GetMapping(params = "email")
    public ResponseEntity<Account> findByEmail(@RequestParam(name = "email") String email) {
        Account account = accountService.findByEmail(email);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    /**
     * Tạo mới một tài khoản.
     * Endpoint: POST /api/accounts
     *
     * @param account Đối tượng Account truyền lên dạng JSON từ Request Body
     * @return ResponseEntity chứa Account vừa tạo và mã trạng thái HTTP 201 CREATED
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Account account) {
        Account createdAccount = accountService.create(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    /**
     * Cập nhật thông tin tài khoản theo ID.
     * Endpoint: PUT /api/accounts/{id}
     *
     * @param id ID của tài khoản cần cập nhật (truyền qua Path Variable)
     * @param account Đối tượng Account chứa các thông tin cập nhật từ Request Body
     * @return ResponseEntity chứa Account sau khi cập nhật và mã trạng thái HTTP 200 OK
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody Account account) {
        Account updatedAccount = accountService.update(id, account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    /**
     * Xóa tài khoản theo ID.
     * Endpoint: DELETE /api/accounts/{id}
     *
     * @param id ID của tài khoản cần xóa (truyền qua Path Variable)
     * @return ResponseEntity chứa thông điệp thông báo xóa thành công và mã trạng thái HTTP 200 OK
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        accountService.delete(id);
        return new ResponseEntity<>("Delete success!", HttpStatus.OK);
    }
}

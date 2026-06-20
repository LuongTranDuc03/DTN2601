package com.vti.controller;

import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountUpdateForm;
import com.vti.result.AccountDTO;
import com.vti.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller cho đối tượng Account (Tài khoản).
 * Cung cấp các API Endpoint để thực hiện các thao tác CRUD (Create, Read, Update, Delete).
 */
@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountController {

    // Tiêm (Inject) dependency IAccountService để sử dụng các phương thức xử lý nghiệp vụ
    @Autowired
    private IAccountService accountService;

    /**
     * Lấy danh sách tất cả các tài khoản có phân trang và lọc.
     * Endpoint: GET /api/accounts
     *
     * @return ResponseEntity chứa Page<AccountDTO> và mã trạng thái HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<Page<AccountDTO>> findAll(AccountFilterForm form, Pageable pageable) {
        Page<AccountDTO> accounts = accountService.findAll(form, pageable);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    /**
     * Lấy thông tin tài khoản theo ID.
     * Endpoint: GET /api/accounts/{id}
     *
     * @param id ID của tài khoản cần tìm (truyền qua Path Variable)
     * @return ResponseEntity chứa thông tin AccountDTO tìm được và mã trạng thái HTTP 200 OK
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable(name = "id") Integer id) {
        AccountDTO dto = accountService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /*
     * Tìm kiếm tài khoản theo địa chỉ email.
     * Endpoint: GET /api/accounts?email=...
     *
     * @param email Địa chỉ email của tài khoản cần tìm (truyền qua Request Parameter)
     * @return ResponseEntity chứa thông tin AccountDTO tìm được và mã trạng thái HTTP 200 OK
     */
    @GetMapping(params = "email")
    public ResponseEntity<AccountDTO> findByEmail(@RequestParam(name = "email") String email) {
        AccountDTO dto = accountService.findByEmail(email);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /*
     * Tạo mới một tài khoản.
     * Endpoint: POST /api/accounts
     *
     * @param  Đối tượng Account truyền lên dạng JSON từ Request Body
     * @return ResponseEntity chứa AccountDTO vừa tạo và mã trạng thái HTTP 201 CREATED
     */
    @PostMapping
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountCreateForm accountCreateForm) {
        Account account = accountService.create(accountCreateForm);
        AccountDTO dto = new AccountDTO(account);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    /*
     * Cập nhật thông tin tài khoản theo ID.
     * Endpoint: PUT /api/accounts/{id}
     *
     * @param id ID của tài khoản cần cập nhật (truyền qua Path Variable)
     * @param account Đối tượng Account chứa các thông tin cập nhật từ Request Body
     * @return ResponseEntity chứa AccountDTO sau khi cập nhật và mã trạng thái HTTP 200 OK
     */
     @PutMapping(value = "/{id}")
     public ResponseEntity<AccountDTO> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody AccountUpdateForm form) {
         AccountDTO dto = accountService.update(id, form);
         return new ResponseEntity<>(dto, HttpStatus.OK);
     }

    /*
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

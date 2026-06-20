# FORM (Request/Form Object) - Đối tượng nhận dữ liệu yêu cầu

## 1. FORM là gì?
**FORM (hoặc Request Object)** là các đối tượng được sử dụng để nhận dữ liệu gửi lên từ phía Client thông qua các request HTTP (thông dụng nhất là JSON Body trong phương thức POST/PUT, hoặc Query Parameters trong phương thức GET).

Trong project Spring Boot, các lớp Form đóng vai trò trung gian nhận dữ liệu, thực hiện kiểm tra tính hợp lệ của dữ liệu (validation) trước khi đưa dữ liệu vào xử lý ở tầng Service và lưu xuống cơ sở dữ liệu dưới dạng Entity.

---

## 2. Tại sao cần sử dụng FORM?

1. **Ràng buộc dữ liệu (Data Binding):** Spring Boot tự động ánh xạ (bind) dữ liệu từ request HTTP (JSON, form-data, query parameters) vào các thuộc tính của đối tượng Form nhờ vào các annotation như `@RequestBody` hoặc `@ModelAttribute`.
2. **Kiểm chuẩn dữ liệu (Validation / Validation Layer):** 
   * Tránh việc viết các câu lệnh `if-else` lặp đi lặp lại để kiểm tra xem dữ liệu client gửi lên có bị trống, có đúng định dạng email, hay độ dài hợp lệ hay không.
   * Sử dụng thư viện Validation chuẩn của Java (`jakarta.validation.constraints`) để khai báo trực tiếp các ràng buộc trên các trường của Form.
3. **Tách biệt dữ liệu đầu vào và Entity:**
   * Khi tạo mới hoặc cập nhật một đối tượng, Client chỉ cần gửi lên các thông tin cần thiết (ví dụ: `departmentId` kiểu số nguyên). 
   * Trong khi đó, Entity cần một đối tượng liên kết hoàn chỉnh (đối tượng `Department`). Form sẽ đóng vai trò giữ ID này để Service tìm kiếm và nạp Entity tương ứng từ Database.

---

## 3. Phân loại FORM trong Project

Project hiện tại sử dụng 3 loại Form chính:

### 3.1. Create Form (Dùng để tạo mới)
Ví dụ: `AccountCreateForm.java`
* Nhận các thông tin bắt buộc khi đăng ký/tạo tài khoản mới.
* Áp dụng các annotation validation chặt chẽ:

```java
public class AccountCreateForm {

    @NotBlank(message = "username không được để trống")
    @Length(max = 100, message = "username không được dài quá 100 ký tự")
    private String username;
    
    private String password;

    @NotBlank(message = "Email không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email không đúng định dạng")
    private String email;
    
    private String fullName;
    private Integer departmentId; // Chỉ nhận ID từ Client gửi lên
    private Integer positionId;   // Chỉ nhận ID từ Client gửi lên
    private LocalDate createDate;
}
```

### 3.2. Update Form (Dùng để cập nhật)
Ví dụ: `AccountUpdateForm.java` hoặc `DepartmentUpdateForm.java`
* Thường chỉ chứa các trường được phép sửa đổi (ví dụ: cập nhật account không cho sửa `username` hay `email` nên Form cập nhật sẽ bỏ các trường này đi, chỉ giữ lại `fullName`, `departmentId`, `positionId`).

### 3.3. Filter Form (Dùng để tìm kiếm/lọc dữ liệu)
Ví dụ: `AccountFilterForm.java`
* Dùng để nhận các tiêu chí tìm kiếm từ Query parameters (như `search`, `minId`, `maxId`).
* Không cần validation quá khắt khe, chỉ dùng để chứa tham số lọc.

---

## 4. Cách sử dụng FORM trong Controller

Để kích hoạt tính năng kiểm chuẩn dữ liệu tự động cho Form, chúng ta sử dụng annotation `@Valid` (hoặc `@Validated`) trong các hàm xử lý của Controller:

```java
@RestController
@RequestMapping(value = "api/v1/accounts")
@Validated
public class AccountController {

    @Autowired
    private IAccountService service;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountCreateForm form) {
        // Nếu dữ liệu trong form không hợp lệ (ví dụ email sai định dạng),
        // Spring Boot sẽ tự động trả về lỗi Bad Request (400) kèm message cấu hình mà không chạy vào trong hàm này.
        service.createAccount(form);
        return new ResponseEntity<>("Create successfully!", HttpStatus.CREATED);
    }
}
```
* **`@RequestBody`**: Đọc dữ liệu JSON từ Client và map vào đối tượng `form`.
* **`@Valid`**: Kích hoạt việc kiểm tra các annotation ràng buộc trong lớp `AccountCreateForm`.
* **Xử lý Exception lỗi**: Nếu validation thất bại, Spring sẽ ném ra `MethodArgumentNotValidException`. Bạn có thể bắt exception này ở lớp Global Exception Handler để trả về thông báo lỗi thân thiện cho client.

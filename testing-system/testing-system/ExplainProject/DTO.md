# DTO (Data Transfer Object) - Đối tượng chuyển truyền dữ liệu

## 1. DTO là gì?
**DTO (Data Transfer Object)** là một Design Pattern được sử dụng để truyền dữ liệu giữa các lớp (layers) khác nhau trong một ứng dụng (chủ yếu là giữa **Controller** và **Client**, hoặc giữa **Service** và **Controller**).

Trong project Spring Boot, DTO là các lớp POJO (Plain Old Java Object) đơn giản chỉ chứa các thuộc tính (fields), hàm tạo (constructors) và các phương thức getter/setter. DTO **không chứa logic nghiệp vụ** (business logic) và **không đại diện cho các bảng trong database** (khác với Entity).

---

## 2. Tại sao cần sử dụng DTO?

Trong dự án thực tế, chúng ta không nên trả trực tiếp đối tượng **Entity** về cho Client vì các lý do sau:

1. **Bảo mật thông tin:** Entity thường chứa các trường nhạy cảm như `password`, `salt`, `token`, hoặc các thông tin nội bộ của hệ thống mà Client không cần hoặc không được phép biết.
2. **Tối ưu hóa băng thông (Payload):** Client có thể chỉ cần hiển thị một vài trường thông tin, việc gửi toàn bộ Entity (với rất nhiều trường dữ liệu thừa) sẽ gây lãng phí tài nguyên mạng.
3. **Tránh lỗi vòng lặp vô hạn (Infinite Recursion):** Khi cấu hình các mối quan hệ `@OneToMany` hoặc `@ManyToMany` giữa các Entity (ví dụ: `Account` liên kết với `Department`, và `Department` chứa danh sách các `Account`), việc serialize trực tiếp Entity sang JSON sẽ dẫn đến lỗi vòng lặp vô hạn.
4. **Phẳng hóa cấu trúc dữ liệu (Flattening):** 
   * Entity chứa các đối tượng liên kết phức tạp (ví dụ: `account.getDepartment()` trả về một Entity `Department`).
   * DTO cho phép chúng ta biến đổi cấu trúc này thành các kiểu dữ liệu đơn giản hơn để Client dễ xử lý (ví dụ: gộp lại thành `String departmentName`).

---

## 3. Phân tích DTO trong Project (Ví dụ: `AccountDTO`)

Dưới đây là cấu trúc của `AccountDTO` trong project:

```java
public class AccountDTO {
    private Integer id;
    private String email;
    private String username;
    private String fullName;
    private String department; // Được phẳng hóa từ Entity Department (lấy name)
    private String position;   // Được phẳng hóa từ Entity Position (lấy positionName)
    private LocalDate createDate;
    
    // Constructor không tham số và có tham số...

    // Constructor ánh xạ thủ công từ Entity Account sang DTO
    public AccountDTO(Account account) {
        if (account != null) {
            this.id = account.getId();
            this.email = account.getEmail();
            this.username = account.getUsername();
            this.fullName = account.getFullName();
            if (account.getDepartment() != null) {
                this.department = account.getDepartment().getName();
            }
            if (account.getPosition() != null && account.getPosition().getPositionName() != null) {
                this.position = account.getPosition().getPositionName().toString();
            }
            if (account.getCreateDate() != null) {
                this.createDate = account.getCreateDate();
            }
        }
    }
}
```

### Điểm đặc biệt:
* Các trường liên kết như `department` và `position` trong DTO chỉ là các chuỗi `String` đơn giản, giúp Client hiển thị dễ dàng mà không cần phải parse các đối tượng JSON lồng nhau.
* Có sẵn constructor nhận vào đối tượng `Account` (Entity) để phục vụ cho việc ánh xạ thủ công nhanh chóng.

---

## 4. Cách chuyển đổi Entity sang DTO

Có 2 cách phổ biến được áp dụng trong project:

### Cách 1: Sử dụng Constructor thủ công (Manual Mapping)
```java
Account account = accountRepository.findById(id).orElse(null);
AccountDTO dto = new AccountDTO(account);
```

### Cách 2: Sử dụng thư viện ModelMapper (Tự động)
Cấu hình và tiêm `ModelMapper` vào service, sau đó map tự động:
```java
Account account = accountRepository.findById(id).orElse(null);
AccountDTO dto = modelMapper.map(account, AccountDTO.class);
```
*(ModelMapper sẽ tự động tìm các trường trùng tên hoặc tự động phân tích `getDepartment().getName()` để gán cho trường `department` của DTO).*

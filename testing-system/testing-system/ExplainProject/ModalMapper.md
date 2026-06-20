# Hướng dẫn sử dụng ModelMapper trong Project

## 1. ModelMapper là gì?
**ModelMapper** là một thư viện Java giúp tự động ánh xạ (mapping) dữ liệu giữa các đối tượng có cấu trúc khác nhau hoặc tương tự nhau. 

Trong các ứng dụng Spring Boot (theo mô hình 3 lớp), ModelMapper thường được sử dụng để chuyển đổi qua lại giữa:
* **Entity** (Thực thể mapping trực tiếp với database table).
* **DTO (Data Transfer Object)** (Đối tượng vận chuyển dữ liệu trả về cho Client/API, giúp ẩn đi các trường thông tin nhạy cảm hoặc không cần thiết).

---

## 2. Cấu hình ModelMapper trong Project

### Bước 1: Khai báo dependency trong `pom.xml`
Để sử dụng ModelMapper, thư viện đã được thêm vào file `pom.xml`:
```xml
<!-- Source: https://mvnrepository.com/artifact/org.modelmapper/modelmapper -->
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>3.2.6</version>
    <scope>compile</scope>
</dependency>
```

### Bước 2: Khai báo Bean trong Spring Container
Để Spring có thể quản lý và Inject (tiêm) đối tượng `ModelMapper` vào các Service, một lớp cấu hình đã được tạo tại `com.vti.config.ModelMapperConfig`:
```java
package com.vti.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
```
* Annotation `@Configuration` đánh dấu đây là lớp cấu hình Spring.
* Annotation `@Bean` tạo ra một đối tượng `ModelMapper` dùng chung cho toàn bộ project.

---

## 3. Cách sử dụng ModelMapper trong Service

Khi cần chuyển đổi danh sách các thực thể `Account` sang `AccountDTO`, ta inject `ModelMapper` và gọi hàm `map()`:

```java
@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper; // Inject bean ModelMapper đã cấu hình

    @Override
    public List<AccountDTO> findAll() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> dtos = new ArrayList<>();
        
        for (Account acc : accounts) {
            // Sử dụng modelMapper để tự động chuyển đổi từ Account sang AccountDTO
            AccountDTO dto = modelMapper.map(acc, AccountDTO.class);
            dtos.add(dto);
        }

        return dtos;
    }
    
    // ...
}
```

### Cách thức ModelMapper ánh xạ tự động:
* **Các thuộc tính phẳng (Flat properties):** ModelMapper sẽ tự động tìm các thuộc tính có cùng tên và kiểu dữ liệu ở lớp nguồn (`Account`) và lớp đích (`AccountDTO`) để gán giá trị (ví dụ: `id` -> `id`, `email` -> `email`, `fullName` -> `fullName`).
* **Các thuộc tính lồng nhau (Nested properties / Implicit Mapping):** ModelMapper có khả năng tự động phân tích đường dẫn thuộc tính. Ví dụ:
  * Trong `Account` có thực thể liên kết `Department` và trong `Department` có thuộc tính `name`.
  * Khi ánh xạ sang `AccountDTO`, nếu `AccountDTO` có thuộc tính `department` (kiểu String), ModelMapper sẽ thông minh nhận diện mối liên kết thông qua chuỗi getter: `account.getDepartment().getName()` và tự động gán vào trường `department` của DTO.

---

## 4. Ưu điểm của việc dùng ModelMapper
1. **Tránh viết mã lặp (Boilerplate code):** Không cần viết thủ công các hàm khởi tạo phức tạp hay gọi hàng loạt hàm `set...()`/`get...()`.
2. **Dễ bảo trì:** Khi thêm thuộc tính mới vào Entity và DTO có cùng tên, ModelMapper sẽ tự động ánh xạ mà không cần sửa đổi code logic chuyển đổi.
3. **Linh hoạt:** Hỗ trợ cấu hình nâng cao (Matching Strategy, Converters, Skip fields) để xử lý các nghiệp vụ ánh xạ phức tạp.

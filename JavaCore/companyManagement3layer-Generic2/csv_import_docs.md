# Tài liệu Nghiệp vụ & Cấu trúc chức năng CSV Import

Dự án này được thiết kế theo kiến trúc 3 lớp (3-layer Architecture) kết hợp với các mẫu thiết kế hướng Generic để xử lý nghiệp vụ nhập dữ liệu (Import) từ file CSV một cách tối ưu và tái sử dụng được mã nguồn.

---

## 1. Cấu trúc thư mục & Vai trò của các File/Folder

Thư mục nguồn `src/main/java/com/zalo/auto` được phân chia thành các package rõ ràng:

```
src/main/java/com/zalo/auto
│
├── frontend/                     # Lớp Giao diện (giao tiếp trực tiếp với người dùng qua Console)
│   ├── Main.java                 # Entry-point của ứng dụng, hiển thị menu chính.
│   ├── DepartmentFunction.java   # Xử lý các lựa chọn menu liên quan tới Phòng ban (gồm chức năng Import).
│   ├── AccountFunction.java      # Xử lý các lựa chọn menu liên quan tới Nhân viên.
│   └── PositionFunction.java     # Xử lý các lựa chọn menu liên quan tới Chức vụ.
│
├── backend/
│   ├── controller/               # Lớp Điều khiển (nhận yêu cầu từ Frontend và điều phối dịch vụ)
│   │   ├── DepartmentController.java
│   │   ├── AccountController.java
│   │   └── PositionController.java
│   │
│   ├── service/                  # Lớp Nghiệp vụ (chứa logic nghiệp vụ cốt lõi)
│   │   ├── IImportFile.java      # [Generic] Interface định nghĩa quy trình import file tổng quát.
│   │   ├── IDepartmentSevice.java# Interface định nghĩa các nghiệp vụ của Phòng ban.
│   │   ├── csv/                  # Gói chứa các lớp thực thi import CSV cụ thể.
│   │   │   └── DepartmentCsvImport.java # Triển khai import CSV riêng cho Phòng ban.
│   │   └── impl/                 # Các lớp triển khai Service.
│   │       └── DepartmentServiceImplement.java
│   │
│   └── repository/               # Lớp Truy xuất dữ liệu (tương tác trực tiếp với Database qua JDBC)
│       ├── IDepartmentRepository.java
│       └── impl/
│           ├── DepartmentRepositoryImplement.java # Triển khai các câu lệnh SQL cho Phòng ban.
│           └── ...
│
├── dto/                          # Lớp Đối tượng truyền tải dữ liệu (Data Transfer Object)
│   ├── ImportError.java          # Đối tượng lưu thông tin lỗi khi import (dòng, giá trị lỗi, mô tả).
│   ├── context/                  # Chứa dữ liệu ngữ cảnh phục vụ việc kiểm tra validation nhanh hơn.
│   │   ├── DepartmentContext.java# Cache danh sách phòng ban từ DB & theo dõi trùng lặp trong file.
│   │   └── AccountContext.java
│   └── csv/                      # Đối tượng ánh xạ từ một dòng trong file CSV.
│       ├── DepartmentCsv.java    # Ánh xạ dòng dữ liệu từ csv của Phòng ban.
│       └── AccountCsv.java
│
├── entity/                       # Các thực thể ánh xạ trực tiếp từ các bảng trong database
│   ├── Department.java           # Thực thể Phòng ban (department_id, department_name).
│   ├── Account.java
│   └── Position.java
│
└── utils/                        # Các công cụ tiện ích dùng chung
    └── Jdbc.java                 # Quản lý kết nối Database (Connection, Statement, ResultSet).
```

---

## 2. Thiết kế chi tiết luồng nghiệp vụ CSV Import

Tính năng CSV Import trong dự án sử dụng **Template Method Pattern** thông qua một Interface Generic `IImportFile<T, E, K>` giúp trừu tượng hóa toàn bộ các bước hoạt động của quá trình import file.

### 2.1. Interface Generic `IImportFile<T, E, K>`
* **Tham số Generic:**
  * `T`: DTO đại diện cho một dòng trong file CSV (Ví dụ: `DepartmentCsv`).
  * `E`: Lớp Context chứa dữ liệu cache từ DB để phục vụ validation (Ví dụ: `DepartmentContext`).
  * `K`: Thực thể cuối cùng hoặc kiểu dữ liệu sẽ lưu xuống DB (Ví dụ: `String` hoặc `Department`).
* **Quy trình hoạt động (phương thức `default String importFile(String path, E context)`):**
  ```mermaid
  graph TD
      A[Bắt đầu Import] --> B{Kiểm tra file hợp lệ?}
      B -- Không --> C[Trả về thông báo lỗi: File không tồn tại / Sai đuôi .csv]
      B -- Có --> D[readFile: Đọc toàn bộ các dòng trong file thành List DTO]
      D --> E{Duyệt qua từng dòng DTO}
      E --> F[validation: Kiểm tra dữ liệu hợp lệ dựa trên Context]
      F --> G{Có lỗi validation nào không?}
      G -- Có --> H[exportFileError: Xuất toàn bộ lỗi ra file import_errors.txt]
      G -- Không --> I[saveAll: Thực hiện ghi dữ liệu hàng loạt xuống DB]
      I --> J[exportSuccessFile: Tạo file báo cáo thành công imported_success_departments.txt]
      H --> K[Kết thúc: Trả về thông báo Thất bại]
      J --> L[Kết thúc: Trả về thông báo Thành công]
  ```

---

## 3. Quy tắc Validation & Cơ chế hoạt động của Phòng ban (Department)

Khi thực hiện import dữ liệu phòng ban từ file CSV (`phongBan.csv`):

### 3.1. Dữ liệu đầu vào kì vọng (CSV)
File CSV gồm tiêu đề `department_name` ở dòng đầu tiên, các dòng sau chứa tên phòng ban:
```csv
department_name
trung tâm tin học
phòng hành chính
giải quyết quyền lợi
```

### 3.2. Cơ chế Tối ưu hóa hiệu năng (DepartmentContext)
Thay vì mỗi dòng trong CSV lại thực hiện câu truy vấn `SELECT` vào database để kiểm tra trùng lặp (gây nghẽn và tải cao cho DB), dự án sử dụng `DepartmentContext`:
* Hệ thống truy vấn toàn bộ các phòng ban hiện có từ DB **một lần duy nhất** khi bắt đầu nghiệp vụ.
* Đưa chúng vào một `Map<String, Department> existingNamesMap` dạng **key viết thường** để so sánh cực nhanh và không phân biệt chữ hoa/thường.
* Đồng thời duy trì `Map<String, Boolean> csvNamesMapLower` để theo dõi các bản ghi đang được kiểm tra trong file CSV hiện tại (tránh trường hợp file CSV chứa 2 dòng trùng tên nhau).

### 3.3. Các quy tắc validation nghiêm ngặt:
Trong phương thức `validation` của `DepartmentCsvImport`:
1. **Trống (Empty):** Tên phòng ban không được để trống hoặc chỉ chứa dấu cách.
2. **Trùng lặp trong File:** Kiểm tra xem tên phòng ban hiện tại đã xuất hiện ở các dòng trước của file CSV chưa (sử dụng `csvNamesMapLower`).
3. **Đã tồn tại trong Database:** Kiểm tra tên phòng ban đã được đặt cho bất kỳ phòng ban nào trước đó trong cơ sở dữ liệu chưa (sử dụng `existingNamesMap`).

### 3.4. Tính toàn vẹn dữ liệu (Transaction Rollback):
Nghiệp vụ áp dụng cơ chế **"Tất cả hoặc không gì cả" (All or Nothing)**:
* Nếu phát hiện **bất kỳ một dòng nào lỗi**, hệ thống sẽ hủy bỏ toàn bộ việc import, **không** lưu bất kỳ phòng ban nào vào database. Đồng thời, toàn bộ lỗi được xuất ra file `import_errors.txt` kèm theo số dòng cụ thể để người dùng chỉnh sửa.
* Chỉ khi toàn bộ file CSV hợp lệ hoàn toàn, hệ sinh thái repository mới kích hoạt Transaction: tắt Auto-Commit, thực thi lưu dữ liệu hàng loạt (Batch Execution) và `commit()`. Nếu phát hiện lỗi cơ sở dữ liệu đột xuất, hệ thống sẽ thực hiện `rollback()` ngay lập tức để tránh rác dữ liệu.

package backend;

import connection.Jdbc;
import entity.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QLDepartment {

    // Lấy tất cả dữ liệu phòng ban từ database
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM department";

        try (Connection conn = Jdbc.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Department dept = new Department(
                    rs.getInt("department_id"),
                    rs.getString("department_name")
                );
                departments.add(dept);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy dữ liệu phòng ban!");
            e.printStackTrace();
        }
        return departments;
    }

    // Hiển thị tất cả phòng ban
    public void displayAllDepartments() {
        List<Department> departments = getAllDepartments();

        if (departments.isEmpty()) {
            System.out.println("Không có dữ liệu phòng ban!");
            return;
        }

        System.out.println("\n========== DANH SÁCH PHÒNG BAN ==========");
        System.out.printf("%-15s%-50s\n", "ID Phòng", "Tên Phòng Ban");
        System.out.println("==========================================");

        for (Department dept : departments) {
            System.out.printf("%-15d%-50s\n", 
                dept.getDepartmentId(), 
                dept.getDepartmentName());
        }
        System.out.println("==========================================\n");
    }
}

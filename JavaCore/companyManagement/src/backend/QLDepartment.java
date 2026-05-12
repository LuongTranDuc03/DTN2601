package backend;

import connection.Jdbc;
import entity.Department;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLDepartment {

    // Lấy tất cả dữ liệu phòng ban từ database
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM department ORDER BY department_id ASC";

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
            e.printStackTrace();
        }
        return departments;
    }

    // Lấy phòng ban theo ID
    public Department getDepartmentById(int id) {
        String query = "SELECT * FROM department WHERE department_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Department(rs.getInt("department_id"), rs.getString("department_name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Tạo mới một phòng ban
    public boolean createDepartment(String name) {
        String query = "INSERT INTO department (department_name) VALUES (?)";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa phòng ban theo ID
    public boolean deleteDepartment(int id) {
        String query = "DELETE FROM department WHERE department_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("❌ Lỗi: Không thể xóa phòng ban này (có thể do đang có nhân viên thuộc phòng này)!");
            return false;
        }
    }

    // Cập nhật tên phòng ban theo ID
    public boolean updateDepartment(int id, String newName) {
        String query = "UPDATE department SET department_name = ? WHERE department_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm các phòng ban có nhiều nhân viên nhất
    public List<Department> getDepartmentsWithMostEmployees() {
        List<Department> result = new ArrayList<>();
        String query = "SELECT d.* FROM department d " +
                       "LEFT JOIN account a ON d.department_id = a.department_id " +
                       "GROUP BY d.department_id " +
                       "HAVING COUNT(a.account_id) = (SELECT MAX(c) FROM (SELECT COUNT(a2.account_id) as c FROM department d2 LEFT JOIN account a2 ON d2.department_id = a2.department_id GROUP BY d2.department_id) as t)";
        
        try (Connection conn = Jdbc.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                result.add(new Department(rs.getInt("department_id"), rs.getString("department_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Tìm các phòng ban có ít nhân viên nhất
    public List<Department> getDepartmentsWithLeastEmployees() {
        List<Department> result = new ArrayList<>();
        String query = "SELECT d.* FROM department d " +
                       "LEFT JOIN account a ON d.department_id = a.department_id " +
                       "GROUP BY d.department_id " +
                       "HAVING COUNT(a.account_id) = (SELECT MIN(c) FROM (SELECT COUNT(a2.account_id) as c FROM department d2 LEFT JOIN account a2 ON d2.department_id = a2.department_id GROUP BY d2.department_id) as t)";
        
        try (Connection conn = Jdbc.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                result.add(new Department(rs.getInt("department_id"), rs.getString("department_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

package com.zalo.auto.backend.repository.impl;

import com.zalo.auto.backend.repository.IDepartmentRepository;
import com.zalo.auto.entity.Department;
import com.zalo.auto.utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImplement implements IDepartmentRepository {
    public List<Department> findAll() {
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

            Jdbc.closeConnection(conn,stmt,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Department findById(int id) {
        String query = "SELECT * FROM department WHERE department_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Department(rs.getInt("department_id"), rs.getString("department_name"));
                }
            }
            Jdbc.closeConnection(conn,pstmt, pstmt.getResultSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(int id, String newName) {
        String query = "UPDATE department SET department_name = ? WHERE department_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            boolean result = pstmt.executeUpdate() > 0;
            Jdbc.closeConnection(conn,pstmt,null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM department WHERE department_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            boolean result = pstmt.executeUpdate() > 0;
            Jdbc.closeConnection(conn,pstmt,null);
            return result;
        } catch (Exception e) {
            System.err.println("❌ Lỗi: Không thể xóa phòng ban này (có thể do đang có nhân viên thuộc phòng này)!");
            return false;
        }
    }

    @Override
    public boolean create(String name) {
        String query = "INSERT INTO department (department_name) VALUES (?)";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            boolean result = pstmt.executeUpdate() > 0;
            Jdbc.closeConnection(conn,pstmt,null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Department> findMostEmployees() {
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
            Jdbc.closeConnection(conn, stmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Department> findLeastEmployees() {
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
            Jdbc.closeConnection(conn, stmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

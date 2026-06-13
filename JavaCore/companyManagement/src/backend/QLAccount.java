package backend;

import connection.Jdbc;
import entity.Account;
import entity.Department;
import entity.Position;
import entity.PositionName;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QLAccount {

    // Lấy tất cả dữ liệu nhân viên từ database (kèm mapping đối tượng)
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT a.*, d.department_name, p.position_name " +
                       "FROM account a " +
                       "LEFT JOIN department d ON a.department_id = d.department_id " +
                       "LEFT JOIN position p ON a.position_id = p.position_id " +
                       "ORDER BY a.account_id ASC";

        try (Connection conn = Jdbc.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                accounts.add(mapResultSetToAccount(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // Lấy nhân viên theo ID
    public Account getAccountById(int id) {
        String query = "SELECT a.*, d.department_name, p.position_name " +
                       "FROM account a " +
                       "LEFT JOIN department d ON a.department_id = d.department_id " +
                       "LEFT JOIN position p ON a.position_id = p.position_id " +
                       "WHERE a.account_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAccount(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to map ResultSet to Account object
    private Account mapResultSetToAccount(ResultSet rs) throws Exception {
        LocalDate createDate = null;
        if (rs.getDate("create_date") != null) {
            createDate = rs.getDate("create_date").toLocalDate();
        }

        Department dept = new Department(rs.getInt("department_id"), rs.getString("department_name"));
        Position pos = new Position(rs.getInt("position_id"), PositionName.fromString(rs.getString("position_name")));

        return new Account(
            rs.getInt("account_id"),
            rs.getString("email"),
            rs.getString("full_name"),
            dept,
            pos,
            createDate
        );
    }

    // Tạo mới một nhân viên
    public boolean createAccount(String email, String fullName, int deptId, int posId) {
        String query = "INSERT INTO account (email, full_name, department_id, position_id, create_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, fullName);
            pstmt.setInt(3, deptId);
            pstmt.setInt(4, posId);
            pstmt.setDate(5, Date.valueOf(LocalDate.now()));
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa nhân viên theo ID
    public boolean deleteAccount(int id) {
        String query = "DELETE FROM account WHERE account_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật nhân viên theo ID
    public boolean updateAccount(int id, String email, String fullName, int deptId, int posId) {
        String query = "UPDATE account SET email = ?, full_name = ?, department_id = ?, position_id = ? WHERE account_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, fullName);
            pstmt.setInt(3, deptId);
            pstmt.setInt(4, posId);
            pstmt.setInt(5, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

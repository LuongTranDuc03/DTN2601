package com.zalo.auto.backend.repository.impl;

import com.zalo.auto.backend.repository.IAccountRepository;
import com.zalo.auto.entity.Account;
import com.zalo.auto.entity.Department;
import com.zalo.auto.entity.Position;
import com.zalo.auto.enums.PositionName;
import com.zalo.auto.utils.Jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImplement implements IAccountRepository {

    @Override
    public List<Account> findAll() {
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
            Jdbc.closeConnection(conn, stmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account findById(int id) {
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
                    Account account = mapResultSetToAccount(rs);
                    Jdbc.closeConnection(conn, pstmt, rs);
                    return account;
                }
            }
            Jdbc.closeConnection(conn, pstmt, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(String email, String username, String fullName, int departmentId, int positionId) {
        String query = "INSERT INTO account (email, username, full_name, department_id, position_id, create_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            pstmt.setString(3, fullName);
            pstmt.setInt(4, departmentId);
            pstmt.setInt(5, positionId);
            pstmt.setDate(6, Date.valueOf(LocalDate.now()));
            boolean result = pstmt.executeUpdate() > 0;
            Jdbc.closeConnection(conn, pstmt, null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(int id, String email, String username, String fullName, int departmentId, int positionId) {
        String query = "UPDATE account SET email = ?, username = ?, full_name = ?, department_id = ?, position_id = ? WHERE account_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            pstmt.setString(3, fullName);
            pstmt.setInt(4, departmentId);
            pstmt.setInt(5, positionId);
            pstmt.setInt(6, id);
            boolean result = pstmt.executeUpdate() > 0;
            Jdbc.closeConnection(conn, pstmt, null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM account WHERE account_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            boolean result = pstmt.executeUpdate() > 0;
            Jdbc.closeConnection(conn, pstmt, null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Account mapResultSetToAccount(ResultSet rs) throws Exception {
        LocalDate createDate = null;
        Date dbDate = rs.getDate("create_date");
        if (dbDate != null) {
            createDate = dbDate.toLocalDate();
        }

        Department dept = new Department(rs.getInt("department_id"), rs.getString("department_name"));
        Position pos = new Position(rs.getInt("position_id"), PositionName.fromString(rs.getString("position_name")));

        String username = null;
        try {
            username = rs.getString("username");
        } catch (SQLException e) {
            // In case the column doesn't exist in a test DB without username
        }

        return new Account(
            rs.getInt("account_id"),
            rs.getString("email"),
            username,
            rs.getString("full_name"),
            dept,
            pos,
            createDate
        );
    }

    @Override
    public boolean checkExistUsername(String username) {
        String query = "SELECT COUNT(1) FROM account WHERE username = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            Jdbc.closeConnection(conn, pstmt, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistEmail(String email) {
        String query = "SELECT COUNT(1) FROM account WHERE email = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            Jdbc.closeConnection(conn, pstmt, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

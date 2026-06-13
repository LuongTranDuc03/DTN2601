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
    public boolean create(String email, String fullName, int departmentId, int positionId) {
        String query = "INSERT INTO account (email, full_name, department_id, position_id, create_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Jdbc.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, fullName);
            pstmt.setInt(3, departmentId);
            pstmt.setInt(4, positionId);
            pstmt.setDate(5, Date.valueOf(LocalDate.now()));
            boolean result = pstmt.executeUpdate() > 0;
            Jdbc.closeConnection(conn, pstmt, null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(int id, String email, String fullName, int departmentId, int positionId) {
        String query = "UPDATE account SET email = ?, full_name = ?, department_id = ?, position_id = ? WHERE account_id = ?";
        try (Connection conn = Jdbc.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, fullName);
            pstmt.setInt(3, departmentId);
            pstmt.setInt(4, positionId);
            pstmt.setInt(5, id);
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

        return new Account(
                rs.getInt("account_id"),
                rs.getString("email"),
                rs.getString("full_name"),
                dept,
                pos,
                createDate);
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

    @Override
    public boolean createAccounts(List<Account> accounts) {
        String query = "INSERT INTO account (email, password, full_name, department_id, position_id, create_date) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Jdbc.getConnection();
            conn.setAutoCommit(false); // Bắt đầu transaction
            pstmt = conn.prepareStatement(query);

            for (Account acc : accounts) {
                pstmt.setString(1, acc.getEmail());
                pstmt.setString(2, acc.getPassword() != null ? acc.getPassword() : "123456");
                pstmt.setString(3, acc.getFullName());

                if (acc.getDepartment() != null) {
                    pstmt.setInt(4, acc.getDepartment().getDepartmentId());
                } else {
                    pstmt.setNull(4, Types.INTEGER);
                }

                if (acc.getPosition() != null) {
                    pstmt.setInt(5, acc.getPosition().getPositionId());
                } else {
                    pstmt.setNull(5, Types.INTEGER);
                }

                pstmt.setDate(6, Date.valueOf(acc.getCreateDate() != null ? acc.getCreateDate() : LocalDate.now()));
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            conn.commit(); // Thành công thì commit
            return true;

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Lỗi thì rollback
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            Jdbc.closeConnection(conn, pstmt, null);
        }
    }
}

package com.zalo.auto.backend.repository.impl;

import com.zalo.auto.backend.repository.IPositionRepository;
import com.zalo.auto.entity.Position;
import com.zalo.auto.enums.PositionName;
import com.zalo.auto.utils.Jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionRepositoryImplement implements IPositionRepository {

    @Override
    public List<Position> findAll() {
        List<Position> positions = new ArrayList<>();
        String query = "SELECT * FROM position ORDER BY position_id ASC";
        try (Connection conn = Jdbc.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                positions.add(new Position(rs.getInt("position_id"), PositionName.fromString(rs.getString("position_name"))));
            }
            Jdbc.closeConnection(conn, stmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return positions;
    }

    @Override
    public Position findById(int id) {
        String query = "SELECT * FROM position WHERE position_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Position position = new Position(rs.getInt("position_id"), PositionName.fromString(rs.getString("position_name")));
                    Jdbc.closeConnection(conn, pstmt, rs);
                    return position;
                }
            }
            Jdbc.closeConnection(conn, pstmt, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(PositionName name) {
        String query = "INSERT INTO position (position_name) VALUES (?)";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name.name()); // Using name() for DB storage
            boolean result = pstmt.executeUpdate() > 0;
            Jdbc.closeConnection(conn, pstmt, null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(int id, PositionName name) {
        String query = "UPDATE position SET position_name = ? WHERE position_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name.name());
            pstmt.setInt(2, id);
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
        String query = "DELETE FROM position WHERE position_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            boolean result = pstmt.executeUpdate() > 0;
            Jdbc.closeConnection(conn, pstmt, null);
            return result;
        } catch (Exception e) {
            System.err.println("❌ Lỗi: Không thể xóa chức vụ này (có thể do đang có nhân viên giữ chức vụ này)!");
            return false;
        }
    }

    @Override
    public List<Position> findMostEmployees() {
        List<Position> result = new ArrayList<>();
        String query = "SELECT p.* FROM position p " +
                       "LEFT JOIN account a ON p.position_id = a.position_id " +
                       "GROUP BY p.position_id " +
                       "HAVING COUNT(a.account_id) = (SELECT MAX(c) FROM (SELECT COUNT(a2.account_id) as c FROM position p2 LEFT JOIN account a2 ON p2.position_id = a2.position_id GROUP BY p2.position_id) as t)";
        try (Connection conn = Jdbc.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                result.add(new Position(rs.getInt("position_id"), PositionName.fromString(rs.getString("position_name"))));
            }
            Jdbc.closeConnection(conn, stmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Position> findLeastEmployees() {
        List<Position> result = new ArrayList<>();
        String query = "SELECT p.* FROM position p " +
                       "LEFT JOIN account a ON p.position_id = a.position_id " +
                       "GROUP BY p.position_id " +
                       "HAVING COUNT(a.account_id) = (SELECT MIN(c) FROM (SELECT COUNT(a2.account_id) as c FROM position p2 LEFT JOIN account a2 ON p2.position_id = a2.position_id GROUP BY p2.position_id) as t)";
        try (Connection conn = Jdbc.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                result.add(new Position(rs.getInt("position_id"), PositionName.fromString(rs.getString("position_name"))));
            }
            Jdbc.closeConnection(conn, stmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

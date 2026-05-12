package backend;

import connection.Jdbc;
import entity.Position;
import entity.PositionName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QLPosition {

    // Lấy tất cả dữ liệu chức vụ từ database
    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        String query = "SELECT * FROM position ORDER BY position_id ASC";

        try (Connection conn = Jdbc.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Position pos = new Position(
                    rs.getInt("position_id"),
                    PositionName.fromString(rs.getString("position_name"))
                );
                positions.add(pos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return positions;
    }

    // Lấy chức vụ theo ID
    public Position getPositionById(int id) {
        String query = "SELECT * FROM position WHERE position_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Position(rs.getInt("position_id"), PositionName.fromString(rs.getString("position_name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Kiểm tra chức vụ đã tồn tại chưa
    public boolean isPositionExists(PositionName name) {
        String query = "SELECT COUNT(*) FROM position WHERE position_name = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name.toString());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tạo mới một chức vụ (Chỉ thêm nếu chưa tồn tại và chưa đủ 4 vị trí)
    public boolean createPosition(PositionName name) {
        if (getAllPositions().size() >= 4) {
            System.out.println("❌ Hệ thống đã có đầy đủ 4 vị trí, không thể thêm mới!");
            return false;
        }
        if (isPositionExists(name)) {
            System.out.println("❌ Vị trí '" + name + "' đã tồn tại trong hệ thống!");
            return false;
        }

        String query = "INSERT INTO position (position_name) VALUES (?)";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name.toString());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa chức vụ theo ID
    public boolean deletePosition(int id) {
        String query = "DELETE FROM position WHERE position_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("❌ Lỗi: Không thể xóa chức vụ này!");
            return false;
        }
    }

    // Cập nhật tên chức vụ theo ID
    public boolean updatePosition(int id, PositionName newName) {
        String query = "UPDATE position SET position_name = ? WHERE position_id = ?";
        try (Connection conn = Jdbc.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newName.toString());
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm các chức vụ có nhiều nhân viên nhất
    public List<Position> getPositionsWithMostEmployees() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Tìm các chức vụ có ít nhân viên nhất
    public List<Position> getPositionsWithLeastEmployees() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

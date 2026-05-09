package backend;

import connection.Jdbc;
import entity.Position;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QLPosition {

    // Lấy tất cả dữ liệu chức vụ từ database
    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        String query = "SELECT * FROM position";

        try (Connection conn = Jdbc.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Position pos = new Position(
                    rs.getInt("position_id"),
                    rs.getString("position_name")
                );
                positions.add(pos);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy dữ liệu chức vụ!");
            e.printStackTrace();
        }
        return positions;
    }

    // Hiển thị tất cả chức vụ
    public void displayAllPositions() {
        List<Position> positions = getAllPositions();

        if (positions.isEmpty()) {
            System.out.println("Không có dữ liệu chức vụ!");
            return;
        }

        System.out.println("\n========== DANH SÁCH CHỨC VỤ ==========");
        System.out.printf("%-15s%-50s\n", "ID Chức Vụ", "Tên Chức Vụ");
        System.out.println("=========================================");

        for (Position pos : positions) {
            System.out.printf("%-15d%-50s\n", 
                pos.getPositionId(), 
                pos.getPositionName());
        }
        System.out.println("=========================================\n");
    }
}

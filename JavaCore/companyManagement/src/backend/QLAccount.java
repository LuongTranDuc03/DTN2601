package backend;

import connection.Jdbc;
import entity.Account;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QLAccount {

    // Lấy tất cả dữ liệu nhân viên từ database
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM account";

        try (Connection conn = Jdbc.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                LocalDate createDate = null;
                if (rs.getDate("create_date") != null) {
                    createDate = rs.getDate("create_date").toLocalDate();
                }

                Account acc = new Account(
                    rs.getInt("account_id"),
                    rs.getString("email"),
                    rs.getString("full_name"),
                    rs.getInt("department_id"),
                    rs.getInt("position_id"),
                    createDate
                );
                accounts.add(acc);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy dữ liệu nhân viên!");
            e.printStackTrace();
        }
        return accounts;
    }

    // Hiển thị tất cả nhân viên
    public void displayAllAccounts() {
        List<Account> accounts = getAllAccounts();

        if (accounts.isEmpty()) {
            System.out.println("Không có dữ liệu nhân viên!");
            return;
        }

        System.out.println("\n==================================== DANH SÁCH NHÂN VIÊN ====================================");
        System.out.printf("%-8s%-30s%-20s%-12s%-12s%-12s\n", 
            "ID", "Email", "Tên Nhân Viên", "ID Phòng", "ID Chức Vụ", "Ngày Tạo");
        System.out.println("=============================================================================================");

        for (Account acc : accounts) {
            System.out.printf("%-8d%-30s%-20s%-12d%-12d%-12s\n",
                acc.getAccountId(),
                acc.getEmail(),
                acc.getFullName(),
                acc.getDepartmentId(),
                acc.getPositionId(),
                acc.getCreateDate() != null ? acc.getCreateDate().toString() : "N/A");
        }
        System.out.println("=============================================================================================\n");
    }
}

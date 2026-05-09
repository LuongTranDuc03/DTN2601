package connection; // Hãy đảm bảo tên thư mục trên máy là 'connection' (viết thường)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {

    // Gom thông tin cấu hình vào đây để dễ quản lý
    private static final String URL = "jdbc:mysql://localhost:3306/CompanyManagement";
    private static final String USER = "root";
    private static final String PASS = "123456";

    // Hàm này dùng để gọi ở mọi nơi trong dự án
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Đảm bảo Driver đã được nạp (cần thiết cho một số phiên bản Java cũ hơn)
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy Driver MySQL!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối Database!");
            e.printStackTrace();
        }
        return conn; // Trả về kết nối để các file khác sử dụng
    }

    public static void main(String[] args) {
        // Kiểm tra thử hàm kết nối
        Connection testConn = getConnection();

        if (testConn != null) {
            System.out.println("Chúc mừng! Kết nối thành công tới Database.");
            try {
                testConn.close(); // Đóng lại sau khi kiểm tra xong
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Kết nối thất bại. Hãy kiểm tra lại URL, User hoặc Password.");
        }
    }
}
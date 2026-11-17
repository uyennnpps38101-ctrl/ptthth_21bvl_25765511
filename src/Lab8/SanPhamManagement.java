package Lab8;

import java.sql.Connection;
import java.sql.SQLException;

public class SanPhamManagement {
    public static void main(String[] args) {
        Connection conn = null;
        SanPhamDAO dao = new SanPhamDAO();

        try {
            conn = DatabaseConnection.getConnection(); //[cite: 1]
            conn.setAutoCommit(false); //[cite: 3]

            dao.taoBang(conn); //[cite: 1]
            dao.themSP(conn, "Laptop", 15000000, 5); //[cite: 1]
            dao.themSP(conn, "Chuột", 200000, 10); //[cite: 1]

            conn.commit(); //[cite: 3]
            System.out.println("=== DANH SÁCH SẢN PHẨM ===");
            dao.hienThi(conn); //[cite: 2]

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback(); //[cite: 3]
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.close(); //[cite: 2]
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
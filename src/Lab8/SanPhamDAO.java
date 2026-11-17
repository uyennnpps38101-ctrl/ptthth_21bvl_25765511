package Lab8;

import java.sql.*;

public class SanPhamDAO {
    // Tạo bảng
    public void taoBang(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS SanPham (maSP INTEGER PRIMARY KEY AUTOINCREMENT, tenSP TEXT, gia REAL, soluong INTEGER)"; //[cite: 1]
        Statement stmt = conn.createStatement(); //[cite: 1]
        stmt.executeUpdate(sql); //[cite: 1]
        stmt.close(); //[cite: 2]
    }

    // Thêm sản phẩm
    public void themSP(Connection conn, String ten, double gia, int sl) throws SQLException {
        String sql = "INSERT INTO SanPham (tenSP, gia, soluong) VALUES ('" + ten + "', " + gia + ", " + sl + ")"; //[cite: 1]
        Statement stmt = conn.createStatement(); //[cite: 1]
        stmt.executeUpdate(sql); //[cite: 1]
        stmt.close(); //[cite: 2]
    }

    // Hiển thị danh sách
    public void hienThi(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement(); //[cite: 1]
        ResultSet rs = stmt.executeQuery("SELECT * FROM SanPham"); //[cite: 2]
        while (rs.next()) { //[cite: 2]
            System.out.println("Mã: " + rs.getInt("maSP") + " | Tên: " + rs.getString("tenSP") + " | Giá: " + rs.getDouble("gia")); //[cite: 2]
        }
        rs.close(); //[cite: 2]
        stmt.close(); //[cite: 2]
    }
}
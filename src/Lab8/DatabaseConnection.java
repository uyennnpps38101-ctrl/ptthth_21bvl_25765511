package Lab8;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC"); //[cite: 1]
        return DriverManager.getConnection("jdbc:sqlite:qlsanpham.db"); //[cite: 1]
    }
}
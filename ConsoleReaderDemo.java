package JavaIOStream.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConsoleReaderDemo {
    public static void main(String[] args) {
        // Sửa lỗi 1: Thêm dấu '(' sau 'new BufferedReader'
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));
        
        int count = 0;
        System.out.println("Nhập văn bản; nhập q để kết thúc:");

        try {
            while (true) {
                String line = reader.readLine();
                if (line == null || line.equalsIgnoreCase("q")) {
                    break;
                }
                count++;
                // Sửa lỗi 3: Thay %%n thành %s%n
                System.out.printf("Dòng %d: %s%n", count, line);
            }
        } catch (IOException e) { 
            // Sửa lỗi 2 & 4: Đưa catch ra ngoài try và sắp xếp lại ngoặc {}
            System.err.println("Không thể đọc dữ liệu: " + e.getMessage());
        }

        System.out.println("Tổng số dòng đã nhập: " + count);
    }
}
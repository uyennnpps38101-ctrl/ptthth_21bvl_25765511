package ThuongKy;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.print("Nhập tên của bạn: ");
            writer.println(scanner.nextLine());

            // Luồng nhận tin từ server
            new Thread(() -> {
                try {
                    String res;
                    while ((res = reader.readLine()) != null) {
                        if (res.startsWith("ROOMS:")) {
                            System.out.println("\n========================================");
                            System.out.println("DANH SÁCH PHÒNG: " + res.substring(6));
                            System.out.println("========================================");
                            System.out.print("-> Chọn phòng (hoặc /exit): ");
                        } else {
                            System.out.println(res);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Đã ngắt kết nối đến server.");
                }
            }).start();

            // Luồng gửi tin đi
            while (true) {
                String input = scanner.nextLine();
                writer.println(input);
                if (input.equalsIgnoreCase("/exit")) break;
            }

            scanner.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Không thể kết nối tới server.");
        }
    }
}
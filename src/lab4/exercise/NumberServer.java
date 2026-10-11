package lab4.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NumberServer {
    private static final String[] DIGITS = {"không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};

    public static String convertNumberToText(String input) {
        try {
            int number = Integer.parseInt(input.trim());
            if (number >= 0 && number <= 9) {
                return DIGITS[number];
            } else {
                return "Số ngoài phạm vi hỗ trợ (0-9)";
            }
        } catch (NumberFormatException e) {
            return "Lỗi: Không phải là số nguyên hợp lệ";
        }
    }

    public static void main(String[] args) {
        int port = 8888;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("NumberServer đang chạy trên cổng " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client đã kết nối từ: " + socket.getRemoteSocketAddress());

                // Xử lý yêu cầu từ client
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                    String clientMessage = reader.readLine();
                    if (clientMessage != null) {
                        System.out.println("Nhận được số từ client: " + clientMessage);
                        String result = convertNumberToText(clientMessage);
                        writer.println(result);
                    }
                } catch (IOException e) {
                    System.err.println("Lỗi xử lý kết nối client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Không thể khởi động server ở cổng " + port + ": " + e.getMessage());
        }
    }
}
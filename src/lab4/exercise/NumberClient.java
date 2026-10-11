package lab4.exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NumberClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 8888;

        try (Socket socket = new Socket(serverAddress, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Gửi một số nguyên lên server (ví dụ: số 5)
            String numberToSend = "5";
            System.out.println("Gửi số lên server: " + numberToSend);
            writer.println(numberToSend);

            // Nhận kết quả từ server trả về
            String response = reader.readLine();
            System.out.println("Kết quả từ Server: " + response);

        } catch (Exception e) {
            System.err.println("Lỗi kết nối đến server: " + e.getMessage());
        }
    }
}
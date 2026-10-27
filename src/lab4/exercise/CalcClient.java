package lab4.exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CalcClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8890);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // "CALC + 100 200"
            // "CALC / 10 0"
            // "CALC % 10 3"
            // "CALC + a 2"
            String command = "CALC + 100 200";
            System.out.println("Gửi lên: " + command);
            writer.println(command);

            String response = reader.readLine();
            System.out.println("Phản hồi từ server: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
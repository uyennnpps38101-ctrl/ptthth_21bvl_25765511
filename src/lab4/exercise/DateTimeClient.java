package lab4.exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DateTimeClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8889);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Đã kết nối Server. Nhập lệnh (DATE, TIME, DATETIME, QUIT):");
            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                writer.println(userInput);
                String response = reader.readLine();
                System.out.println("Server phản hồi: " + response);
                if ("QUIT".equalsIgnoreCase(userInput.trim()) || "BYE".equalsIgnoreCase(response)) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
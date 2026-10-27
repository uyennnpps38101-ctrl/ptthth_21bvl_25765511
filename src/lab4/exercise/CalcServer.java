package lab4.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServer {
    public static String compute(String request) {
        if (request == null || !request.startsWith("CALC")) {
            return "ERR INVALID_FORMAT";
        }
        String[] parts = request.trim().split("\\s+");
        if (parts.length < 4) {
            return "ERR INVALID_FORMAT";
        }

        String operator = parts[1];
        double num1, num2;
        try {
            num1 = Double.parseDouble(parts[2]);
            num2 = Double.parseDouble(parts[3]);
        } catch (NumberFormatException e) {
            return "ERR INVALID_NUMBER";
        }

        double result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) return "ERR DIVIDE_BY_ZERO";
                result = num1 / num2;
                break;
            default:
                return "ERR UNSUPPORTED_OPERATOR";
        }

        if (result == (long) result) {
            return "OK " + (long) result;
        }
        return "OK " + result;
    }

    public static void main(String[] args) {
        int port = 8890;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("CalcServer đang chạy trên cổng " + port + "...");
            while (true) {
                Socket socket = serverSocket.accept();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                    
                    String line = reader.readLine();
                    if (line != null) {
                        System.out.println("Nhận yêu cầu: " + line);
                        String response = compute(line);
                        writer.println(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
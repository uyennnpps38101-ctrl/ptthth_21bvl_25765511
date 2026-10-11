package lab4.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeServer {
    private static final int PORT = 8889;

    private static String getDateTimeResponse(String command) {
        if (command == null) return "ERR INVALID_COMMAND";
        LocalDateTime now = LocalDateTime.now();
        switch (command.trim().toUpperCase()) {
            case "DATE":
                return now.format(DateTimeFormatter.ofPattern("dd MM yyyy"));
            case "TIME":
                return now.format(DateTimeFormatter.ofPattern("HH mm ss"));
            case "DATETIME":
                return now.format(DateTimeFormatter.ofPattern("dd MM yyyy HH mm ss"));
            default:
                return "ERR UNKNOWN_COMMAND";
        }
    }

    public static void main(String[] args) {
        // Chạy UDP Server ở luồng riêng
        new Thread(() -> {
            try (DatagramSocket udpSocket = new DatagramSocket(PORT)) {
                System.out.println("UDP DateTimeServer đang chạy trên cổng " + PORT + "...");
                byte[] buffer = new byte[1024];
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    udpSocket.receive(packet);
                    String cmd = new String(packet.getData(), 0, packet.getLength());
                    String response = getDateTimeResponse(cmd);
                    byte[] sendData = response.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
                    udpSocket.send(sendPacket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Chạy TCP Server
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("TCP DateTimeServer đang chạy trên cổng " + PORT + "...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                         PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                        
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if ("QUIT".equalsIgnoreCase(line.trim())) {
                                writer.println("BYE");
                                break;
                            }
                            writer.println(getDateTimeResponse(line));
                        }
                    } catch (IOException e) {
                        System.err.println("Mất kết nối đột ngột từ client: " + e.getMessage());
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
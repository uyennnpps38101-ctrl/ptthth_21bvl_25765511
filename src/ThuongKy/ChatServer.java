package ThuongKy;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChatServer {
    public static Map<String, List<ClientHandler>> roomList = new LinkedHashMap<>();

    public static void main(String[] args) {
        roomList.put("PhongSach", new ArrayList<>());
        roomList.put("PhongNhac", new ArrayList<>());
        roomList.put("PhongLive", new ArrayList<>());
        roomList.put("PhongGame", new ArrayList<>());

        System.out.println("=== TCP CHAT SERVER ĐÃ SẴN SÀNG ===");
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                new Thread(new ClientHandler(serverSocket.accept())).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
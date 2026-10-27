package ThuongKy;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String clientName, currentRoom;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            clientName = reader.readLine();

            while (true) {
                synchronized (ChatServer.roomList) {
                    writer.println("ROOMS:" + String.join(", ", ChatServer.roomList.keySet()));
                }

                currentRoom = reader.readLine();
                
                // Nếu client gõ /exit hoặc ngắt kết nối ở menu chọn phòng thì thoát luôn
                if (currentRoom == null || currentRoom.equalsIgnoreCase("/exit")) {
                    writer.println("SERVER: Tạm biệt bạn! Hẹn gặp lại.");
                    break;
                }

                // Kiểm tra xem phòng có tồn tại trong danh sách không, nếu không thì báo lỗi và bắt chọn lại
                synchronized (ChatServer.roomList) {
                    if (!ChatServer.roomList.containsKey(currentRoom)) {
                        writer.println("SERVER: Phòng không tồn tại! Vui lòng chọn lại.");
                        continue;
                    }
                    ChatServer.roomList.get(currentRoom).add(this);
                }

                writer.println("SERVER: Đã vào [" + currentRoom + "]. Gõ tin nhắn hoặc /quit để đổi phòng, /exit để thoát.");
                broadcast("--- [" + clientName + " đã tham gia phòng] ---");

                String msg;
                while ((msg = reader.readLine()) != null) {
                    if (msg.equalsIgnoreCase("/quit")) break;
                    if (msg.equalsIgnoreCase("/exit")) break;
                    broadcast(clientName + ": " + msg);
                }

                synchronized (ChatServer.roomList) {
                    List<ClientHandler> list = ChatServer.roomList.get(currentRoom);
                    if (list != null) list.remove(this);
                }
                broadcast("--- [" + clientName + " đã rời phòng] ---");

                if (msg != null && msg.equalsIgnoreCase("/exit")) break;
            }
        } catch (Exception e) {
        } finally {
            try { socket.close(); } catch (Exception e) {}
        }
    }

    public void sendMessage(String msg) { writer.println(msg); }

    private void broadcast(String msg) {
        synchronized (ChatServer.roomList) {
            List<ClientHandler> list = ChatServer.roomList.get(currentRoom);
            if (list != null) for (ClientHandler h : list) h.sendMessage(msg);
        }
    }
}
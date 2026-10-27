package lab65;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    public ChatServiceImpl() throws RemoteException { super(); }
    public String sendMessage(String message) throws RemoteException {
        System.out.println("Client: " + message); return "Server da nhan tin nhan: " + message;
    }
}

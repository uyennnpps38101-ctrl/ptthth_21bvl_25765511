package lab65;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Server { public static void main(String[] args) { try {
    Registry r=LocateRegistry.createRegistry(1099); r.rebind("ChatService",new ChatServiceImpl());
    System.out.println("Chat Server dang chay..."); } catch(Exception e){e.printStackTrace();} } }

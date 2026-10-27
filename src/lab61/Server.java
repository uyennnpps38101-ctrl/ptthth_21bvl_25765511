package lab61;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Server {
    public static void main(String[] args) {
        try { Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("HelloService", new HelloServiceImpl());
            System.out.println("Hello RMI Server dang chay...");
        } catch (Exception e) { e.printStackTrace(); }
    }
}

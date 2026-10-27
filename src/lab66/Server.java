package lab66;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Server { public static void main(String[] args) { try {
    Registry r=LocateRegistry.createRegistry(1099); r.rebind("BankService",new BankServiceImpl());
    System.out.println("Bank Server dang chay. So du ban dau: 1000000 VND"); } catch(Exception e){e.printStackTrace();} } }

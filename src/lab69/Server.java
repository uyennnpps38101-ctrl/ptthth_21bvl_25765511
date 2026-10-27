package lab69;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Server { public static void main(String[] args) { try { Registry r=LocateRegistry.createRegistry(1099);
    r.rebind("AuctionService",new AuctionServiceImpl());System.out.println("Auction Server dang chay..."); }catch(Exception e){e.printStackTrace();} } }

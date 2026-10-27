package lab68;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Server { public static void main(String[] args) { try { Registry r=LocateRegistry.createRegistry(1099);
    r.rebind("FlightService",new FlightServiceImpl());System.out.println("Flight Server dang chay. Chuyen bay: VN101, VN202, VN303"); }catch(Exception e){e.printStackTrace();} } }

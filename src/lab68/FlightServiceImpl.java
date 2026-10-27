package lab68;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
public class FlightServiceImpl extends UnicastRemoteObject implements FlightService {
    private final Map<String,Integer> capacity=new HashMap<>();
    private final Map<String,Integer> booked=new HashMap<>();
    public FlightServiceImpl() throws RemoteException { super(); capacity.put("VN101",100);capacity.put("VN202",80);capacity.put("VN303",60); }
    public synchronized boolean bookTicket(String f,int seats) throws RemoteException {
        if(seats<=0||!capacity.containsKey(f)||seats>availableSeats(f))return false;booked.put(f,booked.getOrDefault(f,0)+seats);return true;
    }
    public synchronized int availableSeats(String f) throws RemoteException { if(!capacity.containsKey(f))return -1;return capacity.get(f)-booked.getOrDefault(f,0); }
    public synchronized boolean cancelBooking(String f,int seats) throws RemoteException {
        int old=booked.getOrDefault(f,0);if(seats<=0||old<seats)return false;booked.put(f,old-seats);return true;
    }
}

package lab69;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface AuctionService extends Remote {
    String addProduct(String name, double startingPrice) throws RemoteException;
    String listProducts() throws RemoteException;
    String placeBid(String productId, String bidder, double amount) throws RemoteException;
}

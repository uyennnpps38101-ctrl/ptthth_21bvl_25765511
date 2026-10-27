package lab69;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
public class AuctionServiceImpl extends UnicastRemoteObject implements AuctionService {
    private static class Product { String id,name,highestBidder; double price; Product(String id,String n,double p){this.id=id;name=n;price=p;highestBidder="Chua co";} }
    private final Map<String,Product> products=new LinkedHashMap<>(); private final AtomicInteger nextId=new AtomicInteger(1);
    public AuctionServiceImpl() throws RemoteException { super(); }
    public synchronized String addProduct(String name,double price) throws RemoteException {
        if(name==null||name.isBlank()||price<=0)return "Ten san pham va gia khoi diem phai hop le.";
        String id="SP"+nextId.getAndIncrement();products.put(id,new Product(id,name,price));return "Da them san pham. Ma san pham: "+id;
    }
    public synchronized String listProducts() throws RemoteException {
        if(products.isEmpty())return "Chua co san pham dau gia.";StringBuilder sb=new StringBuilder();
        for(Product p:products.values())sb.append(p.id).append(" | ").append(p.name).append(" | Gia hien tai: ").append(p.price).append(" | Nguoi dat cao nhat: ").append(p.highestBidder).append("\n");return sb.toString();
    }
    public synchronized String placeBid(String id,String bidder,double amount) throws RemoteException {
        Product p=products.get(id);if(p==null)return "Khong tim thay san pham.";if(bidder==null||bidder.isBlank())return "Ten nguoi dau gia khong hop le.";
        if(amount<=p.price)return "Gia thau phai cao hon gia hien tai: "+p.price;
        p.price=amount;p.highestBidder=bidder;String message="Gia moi cao nhat cho "+p.name+" la "+amount+" (nguoi dat: "+bidder+")";
        System.out.println("THONG BAO: "+message);return message;
    }
}

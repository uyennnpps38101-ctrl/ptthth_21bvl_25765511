package lab69;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client { public static void main(String[] args) { try(Scanner sc=new Scanner(System.in)) {
    Registry r=LocateRegistry.getRegistry("localhost",1099);AuctionService s=(AuctionService)r.lookup("AuctionService");
    while(true){System.out.println("\n1.Them san pham (Seller)  2.Xem san pham  3.Dat gia (Bidder)  0.Thoat");System.out.print("Chon: ");String c=sc.nextLine();if(c.equals("0"))break;
        switch(c){case "1":System.out.print("Ten san pham: ");String n=sc.nextLine();System.out.print("Gia khoi diem: ");double p=Double.parseDouble(sc.nextLine());System.out.println(s.addProduct(n,p));break;
        case "2":System.out.println(s.listProducts());break;
        case "3":System.out.print("Ma san pham: ");String id=sc.nextLine().toUpperCase();System.out.print("Ten nguoi dau gia: ");String bidder=sc.nextLine();System.out.print("Gia thau: ");double bid=Double.parseDouble(sc.nextLine());System.out.println(s.placeBid(id,bidder,bid));break;
        default:System.out.println("Lua chon khong hop le.");}
    }
    }catch(Exception e){e.printStackTrace();} } }

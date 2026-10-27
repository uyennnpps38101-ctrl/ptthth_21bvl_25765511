package lab68;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client { public static void main(String[] args) { try(Scanner sc=new Scanner(System.in)) {
    Registry r=LocateRegistry.getRegistry("localhost",1099);FlightService s=(FlightService)r.lookup("FlightService");
    while(true){System.out.println("\n1.Dat ve  2.Xem ghe trong  3.Huy ve  0.Thoat");System.out.print("Chon: ");String c=sc.nextLine();if(c.equals("0"))break;
        if(c.equals("1")){System.out.print("Ma chuyen bay: ");String f=sc.nextLine().toUpperCase();System.out.print("So ghe: ");int n=Integer.parseInt(sc.nextLine());System.out.println(s.bookTicket(f,n)?"Dat ve thanh cong.":"Dat ve that bai.");}
        else if(c.equals("2")){System.out.print("Ma chuyen bay: ");String f=sc.nextLine().toUpperCase();int n=s.availableSeats(f);System.out.println(n<0?"Khong co chuyen bay nay.":"So ghe trong: "+n);}
        else if(c.equals("3")){System.out.print("Ma chuyen bay: ");String f=sc.nextLine().toUpperCase();System.out.print("So ghe huy: ");int n=Integer.parseInt(sc.nextLine());System.out.println(s.cancelBooking(f,n)?"Huy ve thanh cong.":"Huy ve that bai.");}
        else System.out.println("Lua chon khong hop le.");}
    }catch(Exception e){e.printStackTrace();} } }

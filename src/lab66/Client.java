package lab66;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client { public static void main(String[] args) { try(Scanner sc=new Scanner(System.in)) {
    Registry r=LocateRegistry.getRegistry("localhost",1099); BankService s=(BankService)r.lookup("BankService");
    while(true){System.out.println("\n1.Xem so du  2.Gui tien  3.Rut tien  0.Thoat"); System.out.print("Chon: "); String c=sc.nextLine();
        if(c.equals("0"))break;
        switch(c){case "1":System.out.printf("So du: %.2f VND%n",s.getBalance());break;
        case "2":System.out.print("So tien gui: "); double d=Double.parseDouble(sc.nextLine()); try{s.deposit(d);System.out.println("Gui tien thanh cong.");}catch(Exception e){System.out.println(e.getMessage());}break;
        case "3":System.out.print("So tien rut: "); double w=Double.parseDouble(sc.nextLine());System.out.println(s.withdraw(w)?"Rut tien thanh cong.":"Rut tien that bai (so tien khong hop le/khong du so du).");break;
        default:System.out.println("Lua chon khong hop le.");}
    }
    }catch(Exception e){e.printStackTrace();} } }

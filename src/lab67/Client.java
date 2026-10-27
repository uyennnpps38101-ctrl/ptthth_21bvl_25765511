package lab67;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client { public static void main(String[] args) { try(Scanner sc=new Scanner(System.in)) {
    Registry r=LocateRegistry.getRegistry("localhost",1099); AreaService s=(AreaService)r.lookup("AreaService");
    while(true){System.out.println("\n1.Chu nhat  2.Hinh tron  3.Tam giac  0.Thoat");System.out.print("Chon: ");String c=sc.nextLine();if(c.equals("0"))break;
        switch(c){case "1":System.out.print("Chieu rong: ");double w=Double.parseDouble(sc.nextLine());System.out.print("Chieu dai: ");double h=Double.parseDouble(sc.nextLine());System.out.println("Dien tich = "+s.rectangleArea(w,h));break;
        case "2":System.out.print("Ban kinh: ");System.out.println("Dien tich = "+s.circleArea(Double.parseDouble(sc.nextLine())));break;
        case "3":System.out.print("Canh day: ");double b=Double.parseDouble(sc.nextLine());System.out.print("Chieu cao: ");double th=Double.parseDouble(sc.nextLine());System.out.println("Dien tich = "+s.triangleArea(b,th));break;
        default:System.out.println("Lua chon khong hop le.");}
    }
    }catch(Exception e){e.printStackTrace();} } }

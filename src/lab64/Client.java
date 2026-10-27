package lab64;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client { public static void main(String[] args) { try (Scanner sc = new Scanner(System.in)) {
    Registry r = LocateRegistry.getRegistry("localhost",1099); ContactService s = (ContactService) r.lookup("ContactService");
    while (true) {
        System.out.println("\n1.Them  2.Tim  3.Sua  4.Xoa  5.Xem danh ba  0.Thoat"); System.out.print("Chon: ");
        String choice = sc.nextLine();
        if (choice.equals("0")) break;
        switch (choice) {
            case "1": System.out.print("Ten: "); String n1=sc.nextLine(); System.out.print("SDT: "); s.addContact(n1,sc.nextLine()); System.out.println("Da them."); break;
            case "2": System.out.print("Ten can tim: "); System.out.println(s.findContact(sc.nextLine())); break;
            case "3": System.out.print("Ten can sua: "); String n3=sc.nextLine(); System.out.print("SDT moi: "); System.out.println(s.updateContact(n3,sc.nextLine())?"Da cap nhat.":"Khong tim thay."); break;
            case "4": System.out.print("Ten can xoa: "); System.out.println(s.deleteContact(sc.nextLine())?"Da xoa.":"Khong tim thay."); break;
            case "5": System.out.println(s.listContacts()); break;
            default: System.out.println("Lua chon khong hop le.");
        }
    }
    } catch (Exception e) { e.printStackTrace(); } } }

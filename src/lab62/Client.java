package lab62;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            CalculatorService service = (CalculatorService) registry.lookup("CalculatorService");
            System.out.print("Nhap a: "); int a = sc.nextInt();
            System.out.print("Nhap b: "); int b = sc.nextInt();
            System.out.println("Tong = " + service.add(a, b));
        } catch (Exception e) { e.printStackTrace(); }
    }
}

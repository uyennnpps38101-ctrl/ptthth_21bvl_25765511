package lab63;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client { public static void main(String[] args) { try (Scanner sc = new Scanner(System.in)) {
    Registry r = LocateRegistry.getRegistry("localhost", 1099); PrimeService s = (PrimeService) r.lookup("PrimeService");
    System.out.print("Nhap so nguyen n: "); int n = sc.nextInt();
    System.out.println(n + (s.isPrime(n) ? " la so nguyen to." : " khong la so nguyen to."));
    } catch (Exception e) { e.printStackTrace(); } } }

package lab65;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client { public static void main(String[] args) { try(Scanner sc=new Scanner(System.in)) {
    Registry r=LocateRegistry.getRegistry("localhost",1099); ChatService s=(ChatService)r.lookup("ChatService");
    while(true){System.out.print("Nhap tin nhan (exit de thoat): "); String msg=sc.nextLine(); if(msg.equalsIgnoreCase("exit"))break;
        System.out.println(s.sendMessage(msg));}
    } catch(Exception e){e.printStackTrace();} } }

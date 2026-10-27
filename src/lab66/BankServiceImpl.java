package lab66;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class BankServiceImpl extends UnicastRemoteObject implements BankService {
    private double balance = 1000000.0;
    public BankServiceImpl() throws RemoteException { super(); }
    public synchronized double getBalance() { return balance; }
    public synchronized void deposit(double amount) throws RemoteException {
        if (amount <= 0) throw new RemoteException("So tien gui phai lon hon 0."); balance += amount;
    }
    public synchronized boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) return false; balance -= amount; return true;
    }
}

package lab64;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
public class ContactServiceImpl extends UnicastRemoteObject implements ContactService {
    private final Map<String,String> contacts = new HashMap<>();
    public ContactServiceImpl() throws RemoteException { super(); }
    public synchronized void addContact(String name, String phone) { contacts.put(name, phone); }
    public synchronized String findContact(String name) { return contacts.getOrDefault(name, "Khong tim thay lien he."); }
    public synchronized boolean deleteContact(String name) { return contacts.remove(name) != null; }
    public synchronized boolean updateContact(String name, String newPhone) {
        if (!contacts.containsKey(name)) return false; contacts.put(name, newPhone); return true;
    }
    public synchronized String listContacts() { return contacts.isEmpty() ? "Danh ba rong." : contacts.toString(); }
}

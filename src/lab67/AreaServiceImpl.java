package lab67;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class AreaServiceImpl extends UnicastRemoteObject implements AreaService {
    public AreaServiceImpl() throws RemoteException { super(); }
    public double rectangleArea(double w,double h) throws RemoteException { if(w<0||h<0)throw new RemoteException("Kich thuoc khong duoc am."); return w*h; }
    public double circleArea(double r) throws RemoteException { if(r<0)throw new RemoteException("Ban kinh khong duoc am."); return Math.PI*r*r; }
    public double triangleArea(double b,double h) throws RemoteException { if(b<0||h<0)throw new RemoteException("Kich thuoc khong duoc am."); return b*h/2; }
}

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteTarro extends Remote {
    public void agregarProducto(String producto, int cantidad) throws Exception, RemoteException;
    public String consultarProductos() throws RemoteException, Exception;
}


import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 2000);
        RemoteTarro tarro = (RemoteTarro) registry.lookup("tarro"); //Buscar en el registro...

		try {
			tarro.agregarProducto("A", 1);
            tarro.agregarProducto("B", 2);
            System.out.println(tarro.consultarProductos());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {

            e.printStackTrace();
        }
		
	}
}

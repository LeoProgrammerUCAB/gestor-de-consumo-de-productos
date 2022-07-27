import java.nio.channels.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.gestor.de.cosumo.services.RemoteTarro;

public class ServidorTarro {
    private static ServicioConfiguracion config = new ServicioConfiguracion("./app.config");
    private static RemoteTarro tarro;

    public static void main(String[] args) {
        try {
            tarro = new Tarro();
            Remote remote = UnicastRemoteObject.exportObject(tarro, 0);
            Registry registry = LocateRegistry.createRegistry(config.getPuerto());
            registry.bind("tarro", remote);
            System.out.println("Servidor tarro iniciado");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (java.rmi.AlreadyBoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package com.gestor.de.cosumo.services;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private RemoteTarro getRemoteTarroStub() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 2000);
        return (RemoteTarro) registry.lookup("tarro");
    }

    public void llenar(int cantidad, char tipo) throws Exception {
        RemoteTarro tarro = getRemoteTarroStub();
        System.out.println("llenar " + cantidad + " productos tipo: " + tipo);
        tarro.agregarProducto(tipo, cantidad);
    }

    public void consumir(int cantidad, char tipo) throws Exception {
        RemoteTarro tarro = getRemoteTarroStub();
        System.out.println("consumir " + cantidad + " productos tipo: " + tipo);
        tarro.consumirProducto(tipo, cantidad);
    }

    public String consultar() throws Exception {
        RemoteTarro tarro = getRemoteTarroStub();
        System.out.println("Consultando");
        return tarro.consultarProductos();
    }
}

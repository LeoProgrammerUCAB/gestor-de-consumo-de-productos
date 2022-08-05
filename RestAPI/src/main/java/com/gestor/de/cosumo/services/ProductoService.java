package com.gestor.de.cosumo.services;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductoService {

    private static RemoteTarro tarro;

    public ProductoService() {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("localhost", 2000);
            tarro = (RemoteTarro) registry.lookup("tarro");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void llenar(int cantidad, char tipo) throws Exception {
        // RemoteTarro tarro = getRemoteTarroStub();
        System.out.println("llenar " + cantidad + " productos tipo: " + tipo);
        tarro.agregarProducto(tipo, cantidad);
        System.out.println("Producto llenado");
    }

    public void consumir(int cantidad, char tipo) throws Exception {
        // RemoteTarro tarro = getRemoteTarroStub();
        System.out.println("consumir " + cantidad + " productos tipo: " + tipo);
        try{
            tarro.consumirProducto(tipo, cantidad);       
        }
        catch(Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cantidad de productos insuficientes");
        }
        System.out.println("Producto consumido");
    }

    public String consultar() throws Exception {
        // RemoteTarro tarro = getRemoteTarroStub();
        System.out.println("Consultando");
        try{
            return tarro.consultarProductos();
        }
        catch(Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cantidad de productos insuficientes");
        }
        // System.out.println("Producto consultado");
        // return response;
    }
}

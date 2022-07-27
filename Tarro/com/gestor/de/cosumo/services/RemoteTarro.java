package com.gestor.de.cosumo.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteTarro extends Remote {
    public void agregarProducto(char tipo, int cantidad) throws Exception, RemoteException;

    public void consumirProducto(char tipo, int cantidad) throws Exception, RemoteException;

    public String consultarProductos() throws RemoteException, Exception;
}

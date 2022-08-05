package com.gestor.de.cosumo.services;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespaldoService {
    @Autowired
    private ProductoService productoService;

    public void respaldar() throws Exception {
        //1. Obtener el JSON
        String productos = this.getProductos();
        System.out.println("Transacciones: " + productos);
        //2. Pedir el respaldo al coordinador enviando el JSON
        this.pedirRespaldo(productos);
    }

    public void restaurar() {
        System.out.println("Restaurando");
    }

    private String getProductos() throws Exception {
        return productoService.consultar();
    }

    private void pedirRespaldo(String transacciones) throws UnknownHostException, IOException {
        System.out.println("Pedir respaldo");
        
        Socket s = new Socket("localhost", 5000);
        System.out.println("Conectado al coordinador de replicas");
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        dout.writeUTF("START_2PC;" + transacciones);
        dout.close();
        s.close();
    }
}

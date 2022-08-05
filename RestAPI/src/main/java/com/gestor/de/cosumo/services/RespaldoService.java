package com.gestor.de.cosumo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespaldoService {
    @Autowired
    private ProductoService productoService;

    public void respaldar() throws Exception {
        //1. Obtener el JSON
        String productos = this.getProductos();
        //2. Pedir el respaldo al coordinador enviando el JSON
        this.pedirRespaldo(productos);
    }

    public void restaurar() {
        System.out.println("Restaurando");
    }

    private String getProductos() throws Exception {
        return productoService.consultar();
    }

    private void pedirRespaldo(String productos) {
        System.out.println("Pedir respaldo");
        System.out.println(productos);
    }
}

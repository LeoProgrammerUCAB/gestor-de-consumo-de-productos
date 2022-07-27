package com.gestor.de.cosumo.services;

import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    public void llenar() {
        System.out.println("llenar");
    }

    public void consumir(int cantidad, char tipo) {
        System.out.println("consumir " + cantidad + " productos tipo: " + tipo);
    }

    public String consultar() {
        System.out.println("Consultando");
        return "Hay tantos productos";
    }
}

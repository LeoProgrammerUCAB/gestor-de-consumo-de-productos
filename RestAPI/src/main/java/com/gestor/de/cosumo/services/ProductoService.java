package com.gestor.de.cosumo.services;

import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    public void llenar() {
        System.out.println("llenar");
    }

    public void consumir(int cantidad, char tipo) {
        // Print consumir cantidad tipo
        System.out.println("consumir " + cantidad + " productos tipo: " + tipo);
    }
}

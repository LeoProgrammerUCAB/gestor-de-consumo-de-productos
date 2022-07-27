package com.gestor.de.cosumo.services;

import org.springframework.stereotype.Service;

@Service
public class RespaldoService {
    public void respaldar() {
        System.out.println("Respaldando");
    }

    public void restaurar() {
        System.out.println("Restaurando");
    }
}

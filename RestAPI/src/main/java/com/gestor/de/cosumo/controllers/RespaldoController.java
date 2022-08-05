package com.gestor.de.cosumo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestor.de.cosumo.services.RespaldoService;

@RestController
@RequestMapping("/respaldo")
public class RespaldoController {
    @Autowired
    private RespaldoService respaldoService;

    @PostMapping("/")
    public void respaldar() throws Exception {
        respaldoService.respaldar();
    }

    @PostMapping("/restaurar")
    public void restaurar() {
        respaldoService.restaurar();
    }
}

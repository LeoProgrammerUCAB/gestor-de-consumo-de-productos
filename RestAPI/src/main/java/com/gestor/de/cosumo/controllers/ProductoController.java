package com.gestor.de.cosumo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestor.de.cosumo.DTO.TransaccionDeProductoDTO;
import com.gestor.de.cosumo.services.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping("/llenar")
    public void llenar(@RequestBody TransaccionDeProductoDTO body) throws Exception {
        productoService.llenar(body.cantidad, body.tipo);
    }

    @PostMapping("/consumir")
    public void consumir(@RequestBody TransaccionDeProductoDTO body) throws Exception {
        productoService.consumir(body.cantidad, body.tipo);
    }

    @GetMapping("/consultar")
    public String consultar() throws Exception {
        return productoService.consultar();
    }

}
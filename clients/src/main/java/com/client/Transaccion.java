package com.client;

import java.util.Map;

public class Transaccion {
    private String actor;
    private String fecha;
    private Map<String, Integer> inventario_resultante;
    private char tipo_producto;
    private int cantidad;

    // Getters and Setters
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Map<String, Integer> getInventario_resultante() {
        return inventario_resultante;
    }

    public void setInventario_resultante(Map<String, Integer> inventario_resultante) {
        this.inventario_resultante = inventario_resultante;
    }

    public char getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(char tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String toString() {
        return "Transaccion{\n" +
                "\tcantidad=" + cantidad + ",\n" +
                "\ttipo_producto=" + tipo_producto + ",\n" +
                "\tactor='" + actor + '\'' + ",\n" +
                "\tfecha='" + fecha + '\'' + ",\n" +
                "\tinventario_resultante=" + inventario_resultante + "\n" +
                '}';
    }
}

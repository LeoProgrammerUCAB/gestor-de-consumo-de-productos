package com.client.DTO;

public class TransaccionDeProductoDTO {
    public int cantidad;
    public char tipo;

    public TransaccionDeProductoDTO(char tipo, int cantidad) {
        this.cantidad = cantidad;
        this.tipo = tipo;
    } 

    public String toJsonString(){
        return "{\"tipo\":\"" + this.tipo + "\",\"cantidad\":" + this.cantidad + "}";
        
    }

}

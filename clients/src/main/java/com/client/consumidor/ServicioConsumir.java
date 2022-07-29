package com.client.consumidor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.client.DTO.TransaccionDeProductoDTO;

public class ServicioConsumir {
    
    private String HOST = "http://localhost";
    private String PORT = "8080";
    private String PATH = "/producto/consumir";

    private String URL = HOST + ":" + PORT + PATH;

    public void consumir(char tipo, int cantidad) throws Exception{

        TransaccionDeProductoDTO dto = this.crearDTO(tipo, cantidad);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(dto.toJsonString()))
                .header("Content-Type", "application/json")
                .uri(URI.create(URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 201) {
            System.out.println("Consumo exitoso de " + cantidad + " unidades del producto " + tipo);         
        }else if (response.statusCode() == 400) {
            throw new Exception("No hay suficientes unidades del producto " + tipo);
        }
        else{
            throw new Exception("Error al consumir el producto " + tipo);
        }
    };

    private TransaccionDeProductoDTO crearDTO(char tipo, int cantidad){
        return new TransaccionDeProductoDTO(tipo, cantidad);
    }


};
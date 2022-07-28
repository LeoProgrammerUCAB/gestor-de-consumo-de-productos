package com.client.productor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.client.DTO.TransaccionDeProductoDTO;

public class ServicioLlenado {
    
    private String HOST = "http://localhost";
    private String PORT = "8080";
    private String PATH = "/producto/llenar";

    private String URL = HOST + ":" + PORT + PATH;

    public void llenar(char tipo, int cantidad) throws Exception{

        TransaccionDeProductoDTO dto = this.crearDTO(tipo, cantidad);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(dto.toJsonString()))
                .header("Content-Type", "application/json")
                .uri(URI.create(URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 201) {
            throw new Exception("Error al llenar el productor");
        }else{
            System.out.println("Llenado exitoso del producto "+ tipo + " con " + cantidad + " unidades");
        }
    };

    private TransaccionDeProductoDTO crearDTO(char tipo, int cantidad){
        return new TransaccionDeProductoDTO(tipo, cantidad);
    }


};
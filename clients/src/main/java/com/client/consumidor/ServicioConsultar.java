package com.client.consumidor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.client.Transaccion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServicioConsultar {
    private String HOST = "http://localhost";
    private String PORT = "8080";
    private String PATH = "/producto/consultar";

    private String URL = HOST + ":" + PORT + PATH;

    public void consultar() throws Exception{

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new Exception("Error al consultar las transacciones del tarro");
        }else{
            ObjectMapper mapper = new ObjectMapper();
            List<Transaccion> posts = mapper.readValue(response.body(), new TypeReference<List<Transaccion>>() {
            });
            //Imprime cada transaccion
            posts.forEach(System.out::println);                    
        }


    };
}

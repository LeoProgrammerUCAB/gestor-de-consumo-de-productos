package com.client.productor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServicioRespaldo {
    
    private String HOST = "http://localhost";
    private String PORT = "8080";
    private String PATH = "/respaldo/";

    private String URL = HOST + ":" + PORT + PATH;

    public void respaldar() throws Exception{

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .header("Content-Type", "application/json")
                .uri(URI.create(URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new Exception("Error al respaldar");
        }else{
            System.out.println("Respaldo exitoso");
        }
    };


};
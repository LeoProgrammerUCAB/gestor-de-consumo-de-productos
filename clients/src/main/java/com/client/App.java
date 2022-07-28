package com.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class App {

    public static final String POSTS_API_URL = "http://localhost:8080/producto/consultar";

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // parse JSON
        ObjectMapper mapper = new ObjectMapper();
        List<Transaccion> posts = mapper.readValue(response.body(), new TypeReference<List<Transaccion>>() {
        });

        // posts.forEach(post -> {
        // System.out.println(post.toString());
        // });
        posts.forEach(System.out::println);
    }
}
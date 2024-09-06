package com.api.productos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "https://reqres.in/api/users";

    @Async
    public CompletableFuture<String> getUsers(int page) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("page", page)
                .toUriString();
        String response = restTemplate.getForObject(url, String.class);
        return CompletableFuture.completedFuture(response);
    }
}

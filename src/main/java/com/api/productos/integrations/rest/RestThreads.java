package com.api.productos.integrations.rest;

import com.api.productos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class RestThreads{
    @Autowired
    private UserService userService;

    public CompletableFuture<Optional<String>> fetchUsers(String... args) {
        CompletableFuture<String> future = userService.getUsers(1);

        return future.thenApply(response -> {
           return Optional.ofNullable(response);
        }).exceptionally(e -> {
            e.printStackTrace();
            return Optional.empty();
        });
    }
}

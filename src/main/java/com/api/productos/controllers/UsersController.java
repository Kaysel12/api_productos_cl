package com.api.productos.controllers;

import com.api.productos.dto.UserResponse;
import com.api.productos.integrations.rest.RestThreads;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Path("/api/foreign")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersController {

    private RestThreads restThreads;

	private final ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/fetch-users/{page}")
		public ResponseEntity<List<UserResponse>> fetchUsers(@PathParam("page") int page) {
		try {
			CompletableFuture<Optional<String>> result = restThreads.fetchUsers();
			Optional<String> response = result.get();

			if (response.isPresent()) {
				List<UserResponse> users = extractUsersFromJson(response.get());
				if (users != null) {
					return ResponseEntity.ok(users);
				} else {
					return ResponseEntity.noContent().build();
				}
			} else {
				return ResponseEntity.noContent().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(null);
		}
	}

	private List<UserResponse> extractUsersFromJson(String json) {
		try {
			JsonNode rootNode = objectMapper.readTree(json);
			JsonNode dataNode = rootNode.path("data");

			return StreamSupport.stream(dataNode.spliterator(), false)
					.map(node -> {
						try {
							return objectMapper.treeToValue(node, UserResponse.class);
						} catch (Exception e) {
							e.printStackTrace();
							return null;
						}
					})
					.filter(user -> user != null)
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

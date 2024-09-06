package com.api.productos;

import com.api.productos.integrations.rest.RestThreads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@ServletComponentScan
public class ApiProductosApplication implements CommandLineRunner {

	@Autowired
	private RestThreads restThreads;

	public static void main(String[] args) {
		SpringApplication.run(ApiProductosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {
			CompletableFuture<Optional<String>> result = restThreads.fetchUsers();

			Optional<String> response = result.get();

			response.ifPresentOrElse(
					res -> System.out.println("Response: " + res),
					() -> System.out.println("No response received or an error occurred.")
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

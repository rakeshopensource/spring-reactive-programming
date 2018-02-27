package com.rakesh.productcatalogclient;

import lombok.*;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import static lombok.extern.java.Log.*;

import java.util.Date;

@Log
@SpringBootApplication
public class ProductcatalogclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductcatalogclientApplication.class, args);
	}


	@Bean
	WebClient webClient() {
		return WebClient
				.create("http://localhost:8080/products")
				.mutate()
				.build();
	}

	@Bean
	CommandLineRunner demo(WebClient client) {
		return strings ->
				client
						.get()
						.uri("")
						.retrieve()
						.bodyToFlux(Product.class)
						.flatMap(product ->
								client.get()
										.uri("/{id}/events", product.getId())
										.retrieve()
										.bodyToFlux(ProductEvent.class))
						.subscribe(productEvent -> log.info(productEvent.toString()));
	}


}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ProductEvent {
	private String productId;
	private Date date;
}

@Data
@NoArgsConstructor
@RequiredArgsConstructor
class Product {
	private String id;
	@NonNull
	private String name;
}

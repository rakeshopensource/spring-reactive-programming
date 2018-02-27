package com.rakesh.productcatalogserver;

import com.rakesh.productcatalogserver.bo.ProductEvent;
import com.rakesh.productcatalogserver.document.Product;
import com.rakesh.productcatalogserver.repository.ProductRepository;
import com.rakesh.productcatalogserver.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@SpringBootApplication
@Configuration
@ComponentScan("com.rakesh.productcatalogserver")
@EnableReactiveMongoRepositories("com.rakesh.productcatalogserver.repository")
public class ProductCatalogServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogServerApplication.class, args);
	}

	@Bean
	RouterFunction<?> routes(ProductService productService) {
		return RouterFunctions

				.route(GET("/products"),
						serverRequest -> ServerResponse.ok().body(productService.all(), Product.class))
				.andRoute(GET("/products/{id}"),
						serverRequest -> ServerResponse.ok().body(productService.byId(
								serverRequest.pathVariable("id")), Product.class))
				.andRoute(GET("/products/{id}/events"), serverRequest ->
						ServerResponse.ok()
								.contentType(MediaType.TEXT_EVENT_STREAM)
								.body(productService.events(serverRequest.pathVariable("id")),
										ProductEvent.class));

	}

	@Bean
	CommandLineRunner setup(ProductRepository productRepository) {
		return args -> {
			 productRepository
					.deleteAll()
					.thenMany(
							Flux.just("iPhone", "Mi", "Samsung")
									.flatMap(name -> productRepository.save(new Product(name)))
					).subscribe(null, null,
							() -> productRepository.findAll().subscribe(System.out::println));
		};
	}
}

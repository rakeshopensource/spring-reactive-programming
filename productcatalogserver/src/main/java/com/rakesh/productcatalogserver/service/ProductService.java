package com.rakesh.productcatalogserver.service;


import com.rakesh.productcatalogserver.bo.ProductEvent;
import com.rakesh.productcatalogserver.document.Product;
import com.rakesh.productcatalogserver.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<ProductEvent> events(String productId) {
        return Flux.<ProductEvent>generate(sink -> sink.next(new ProductEvent(productId, new Date())))
                .delayElements(Duration.ofSeconds(1));
    }

    public Mono<Product> byId(String id) {
        return this.productRepository.findById(id);
    }

    public Flux<Product> all() {
        return this.productRepository.findAll();
    }
}
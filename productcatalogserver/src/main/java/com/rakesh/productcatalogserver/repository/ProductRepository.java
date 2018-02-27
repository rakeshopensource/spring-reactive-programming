package com.rakesh.productcatalogserver.repository;


import com.rakesh.productcatalogserver.document.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}
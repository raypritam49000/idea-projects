package com.example.springbootmongodockercompose.repo;

import com.example.springbootmongodockercompose.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {
}

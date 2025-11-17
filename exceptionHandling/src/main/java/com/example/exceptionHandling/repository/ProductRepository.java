package com.example.exceptionHandling.repository;

import com.example.exceptionHandling.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

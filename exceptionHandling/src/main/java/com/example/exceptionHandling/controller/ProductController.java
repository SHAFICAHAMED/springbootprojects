package com.example.exceptionHandling.controller;

import com.example.exceptionHandling.custom.ProductNotFoundException;
import com.example.exceptionHandling.dto.ProductRequest;
import com.example.exceptionHandling.dto.ProductResponse;
import com.example.exceptionHandling.model.Product;
import com.example.exceptionHandling.repository.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        if (request.getPrice() > 10000) {
            throw new IllegalArgumentException("Price too high, max allowed is 10000");
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        product = productRepository.save(product);

        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable @Min(1) Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        return ResponseEntity.ok(response);
    }
}

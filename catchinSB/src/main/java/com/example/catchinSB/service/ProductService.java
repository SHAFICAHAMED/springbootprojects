package com.example.catchinSB.service;

import com.example.catchinSB.model.Product;
import com.example.catchinSB.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable("products")
    public List<Product> getAllProuducts(){
        System.out.println("Fetching from Db");
        return  productRepository.findAll();
    }

    public Optional<Product> getById(long id){
        return  productRepository.findById(id);
    }

    @CacheEvict(value = "products",allEntries = true)
    public  Product addProduct(Product product){
        return  productRepository.save(product);
    }
    @CacheEvict(value = "products",allEntries = true)
    public  Product updateProduct(Long id,Product updateproduct){
        updateproduct.setId(id);
        return  productRepository.save(updateproduct);
    }
    @CacheEvict(value = "products",allEntries = true)
    public  void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}

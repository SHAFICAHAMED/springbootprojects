package com.example.Ecommerce.service;

import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return  productRepository.findAll();
    }


    public  void saveProduct(Product product){
        productRepository.save(product);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            productRepository.save(existingProduct);
        }
    }


    public Optional<Product> getById(Long id) {
        return   productRepository.findById(id);
    }
    public List<Product> searchProducts(String query) {
        return productRepository.findByNameContainingIgnoreCase(query); // Search by product name
    }
}

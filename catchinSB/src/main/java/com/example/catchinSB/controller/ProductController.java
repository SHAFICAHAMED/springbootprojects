package com.example.catchinSB.controller;


import com.example.catchinSB.model.Product;
import com.example.catchinSB.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return  productService.getAllProuducts();
    }

    @PostMapping
    public  Product addProduct(@RequestBody Product product){
        return  productService.addProduct(product);
    }

    @PutMapping("/update/{id}")
    public  Product updateProduct(@PathVariable Long id,@RequestBody Product product){
        return  productService.updateProduct(id,product);
    }

    @DeleteMapping("/delete/{id}")
    public  void  deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}

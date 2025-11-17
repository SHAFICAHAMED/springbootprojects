package com.example.Ecommerce.controller;

import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute("products", productService.getAllProducts());
        return "admin-product-list";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute("product", new Product());
        return "admin-add-product";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            String uploadDir = "uploads/";
            String fileName = imageFile.getOriginalFilename();

            try {
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                product.setImageName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

@GetMapping ("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin-edit-product";
    }

//    @PostMapping("/update/{id}")
//    public String updateProduct(@PathVariable("id") Long id,
//                                @ModelAttribute("product") Product updatedProduct) {
//        // Ensure that you update the product correctly
//        productService.updateProduct(id, updatedProduct);
//        return "redirect:/admin/products";
//    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @ModelAttribute("product") Product updatedProduct,
                                @RequestParam("imageFile") MultipartFile imageFile) {

        Product existingProduct = productService.getProductById(id);

        // Update fields
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());

        // Image handling
        if (!imageFile.isEmpty()) {
            String uploadDir = "uploads/";
            String fileName = imageFile.getOriginalFilename();

            try {
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                existingProduct.setImageName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productService.saveProduct(existingProduct);
        return "redirect:/admin/products";
    }


    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = productService.getById(id);
        if (optionalProduct.isPresent()) {
            model.addAttribute("product", optionalProduct.get());
            return "product-details";
        } else {
            return "redirect:/error";
        }
    }

}

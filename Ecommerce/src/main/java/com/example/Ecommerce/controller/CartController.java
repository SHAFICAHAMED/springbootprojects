package com.example.Ecommerce.controller;

import com.example.Ecommerce.entity.Cart;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    // Show Cart Page
    @GetMapping
    public String viewCart(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        User user = (User) session.getAttribute("user");
        Long userId = user.getId();

        List<Cart> cartItems = cartService.getCartItems(userId);
        double total = cartItems.stream()
                .mapToDouble(c -> c.getProduct().getPrice() * c.getQuantity())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "cart"; // cart.html page
    }

    // Add Product to Cart
    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        User user = (User) session.getAttribute("user");
        Long userId = user.getId();

        Product product = productService.getProductById(productId);
        cartService.addToCart(Long.valueOf(String.valueOf(userId)), product);

        return "redirect:/api/cart"; // Redirect to cart page
    }

    // Update Cart Quantities
    @PostMapping("/update")
    public String updateCart(@RequestParam("itemIds") List<Long> itemIds,
                             @RequestParam("quantities") List<Integer> quantities,
                             HttpSession session) {
        Long userId = ((User) session.getAttribute("user")).getId();
        cartService.updateCart(userId, itemIds, quantities);
        return "redirect:/api/cart";
    }


//    // Place Order
//    @PostMapping("/order")
//    public ResponseEntity<String> placeOrder(@RequestBody List<Cart> cartItems) {
//        cartService.placeOrder(cartItems);
//        return ResponseEntity.ok("Order placed successfully!");
//    }
    @PostMapping("/order")
    public String placeOrder(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<Cart> cartItems = cartService.getCartItems(user.getId());

        if (cartItems.isEmpty()) {
            return "redirect:/api/cart?empty=true";
        }

        cartService.placeOrder(cartItems); // store order
        return "order-success";  // create this page
    }

}

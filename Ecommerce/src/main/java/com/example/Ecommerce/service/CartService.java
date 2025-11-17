package com.example.Ecommerce.service;

import com.example.Ecommerce.entity.*;
import com.example.Ecommerce.repository.CartRepository;
import com.example.Ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private final Map<String, List<Product>> cartStore = new HashMap<>(); // Store cart by user ID
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;



    public List<Cart> getCartItems(Long userId) {
        return cartRepository.findByUserId(userId);
    }

//    public void placeOrder(List<Cart> cartItems) {
//        cartRepository.saveAll(cartItems);
//    }
    public void addToCart(Long userId, Product product) {
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setQuantity(1);
        User user = new User();
        user.setId(userId);
        cart.setUser(user);
        cartRepository.save(cart);
    }
public void placeOrder(List<Cart> cartItems) {
    if (cartItems == null || cartItems.isEmpty()) return;

    User user = cartItems.get(0).getUser(); // all cart items must belong to the same user

    Order order = new Order();
    order.setUser(user);

    List<OrderItem> orderItems = new ArrayList<>();
    for (Cart cart : cartItems) {
        OrderItem item = new OrderItem();
        item.setOrder(order); // set the parent order
        item.setProduct(cart.getProduct());
        item.setQuantity(cart.getQuantity());
        item.setPrice(cart.getProduct().getPrice()); // assuming price is fetched from Product

        orderItems.add(item);
    }

    order.setItems(orderItems);
    orderRepository.save(order); // cascade will save items too

    cartRepository.deleteAll(cartItems); // clear the cart
}



    public void updateQuantities(List<Long> itemIds, List<Integer> quantities) {
        for (int i = 0; i < itemIds.size(); i++) {
            Cart item = cartRepository.findById(itemIds.get(i)).orElseThrow();
            item.setQuantity(quantities.get(i));
            cartRepository.save(item);
        }
    }
    public void updateCart(Long userId, List<Long> itemIds, List<Integer> quantities) {
        for (int i = 0; i < itemIds.size(); i++) {
            Cart item = cartRepository.findById(itemIds.get(i)).orElseThrow();
            if (item.getUser().getId()==(userId)) { // ensure user owns the cart item
                item.setQuantity(quantities.get(i));
                cartRepository.save(item);
            }
        }
    }


}

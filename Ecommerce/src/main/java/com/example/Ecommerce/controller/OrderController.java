package com.example.Ecommerce.controller;

import com.example.Ecommerce.entity.Order;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
            @RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @GetMapping("/place")
    public String placeOrder(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Long userId = user.getId();

        // Get all cart items for user and place the order
        cartService.placeOrder(cartService.getCartItems(userId));

        return "redirect:/api/order/details"; // Redirect to show the newly placed order
    }

    @GetMapping("/details")
    public String showOrderDetails(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Long userId = user.getId();
        List<Order> orders = orderRepository.findOrdersByUserIdOrderByOrderDateDesc(userId);
        // Calculate total for each order
        Map<Long, Double> orderTotals = new HashMap<>();
        for (Order order : orders) {
            double total = order.getItems().stream()
                    .mapToDouble(item -> item.getPrice() * item.getQuantity())
                    .sum();
            orderTotals.put(order.getId(), total);
        }



        model.addAttribute("orders", orders);
        model.addAttribute("orderTotals", orderTotals);
        return "order-details";
    }
}

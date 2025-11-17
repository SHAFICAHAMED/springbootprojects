package com.example.Ecommerce.controller;

import com.example.Ecommerce.entity.Order;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.repository.OrderRepository;
import com.example.Ecommerce.service.ProductService;
import com.example.Ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private UserService userService;
    private  ProductService productService;
  private OrderRepository orderRepository;

    public UserController(UserService userService, ProductService productService,OrderRepository orderRepository) {
        this.userService = userService;
        this.productService=productService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/register")
    public  String showRegisterForm(Model model){

        model.addAttribute("user",new User());
        return  "register";
    }

    @PostMapping("/register")
    public  String registerUser(@ModelAttribute("user") User user,Model model){
        userService.registerUser(user);
        model.addAttribute("message","Registration successfully please login");
        return  "login";

    }
    @GetMapping
    public  String showLoginForm(Model model){
        model.addAttribute("user",new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, HttpSession session, Model model) {
        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());

        if (loggedInUser != null) {
            session.setAttribute("user", loggedInUser);

            if ("ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/home";
            }
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }


    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("name", user.getName());
            List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);
            return "home";
        }
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }
        model.addAttribute("adminName", user.getName());
        return "admin-dashboard"; //
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




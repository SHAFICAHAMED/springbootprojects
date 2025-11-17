package com.example.clientRest.contro;


import com.example.clientRest.model.User;
import com.example.clientRest.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class restcontro {


    private UserService userserv;

    public restcontro(UserService userserv) {
        this.userserv = userserv;
    }
    @GetMapping("/client")
    public  void open(){
        System.out.println("Fetching users from server...");
        List<User> users = userserv.fetchUsers();
        users.forEach(System.out::println);
    }
    @GetMapping("/add")
    public  void add(){
        System.out.println("\nAdding a new user...");
        User newUser = new User(0, "shafickong", "shafickong@example.com");
        User addedUser = userserv.addUser(newUser);
        System.out.println("Added User: " + addedUser);
    }
    @GetMapping("/del")
    public  void  del(){
        System.out.println("\nDeleting user with ID 1...");
        String deleteResult = userserv.deleteUser(1);
        System.out.println(deleteResult);
    }
    @GetMapping("/up")
    public  void up(){
        System.out.println("\nUpdating user with ID 2...");
        User updatedUser = new User(2, "harishpadmanaban Updated", "bob.updated@example.com");
        User updatedResult = userserv.updateUser(2, updatedUser);
        System.out.println("Updated User: " + updatedResult);
    }


}

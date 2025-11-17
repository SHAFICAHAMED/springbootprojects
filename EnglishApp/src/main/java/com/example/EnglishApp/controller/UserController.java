package com.example.EnglishApp.controller;


import com.example.EnglishApp.model.User;
import com.example.EnglishApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        System.out.println(user.getUsername());
        return  userService.registerUser(user);
    }
    @GetMapping
    public List<User> getAllUsers(){
        return  userService.getAllUser();
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
    @GetMapping("/find")
    public ResponseEntity<User> getByUserName(@RequestParam String username){
        List<User> users =getAllUsers();
        for(User u:users){
            if(u.getUsername().equals(username))
            {
                return  ResponseEntity.ok(u);
            }

        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @DeleteMapping("/delete/{id}")
    public  void deleteUser(@PathVariable("id") long userid){
          userService.deleteUser(userid);
    }
    @PutMapping("/update/{id}")
    public void  UpdateUser(@PathVariable long id, @RequestBody User user){
        user.setId(id);
        userService.updateUser(user);
    }


}

package com.example.userRest.control;
import com.example.userRest.Model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class usercontrol {

    private final List<User> users = new ArrayList<>(List.of(
            new User(1, "shafic", "shafic@gamil.com"),
            new User(2, "harish", "harish@gamil.com"),
            new User(3, "kong", "kong@gamil.com")
    ));
    // GET: Fetch all users
    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    // POST: Add a new user
    @PostMapping
    public User addUser(@RequestBody User user) {
        user.setId(users.size() + 1); // Simulate ID generation
        users.add(user);
        return user;
    }

    // PUT: Update an existing user by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return user;
            }
        }
        throw new RuntimeException("User not found with ID: " + id);
    }

    // DELETE: Remove a user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        users.removeIf(user -> user.getId() == id);
        return "User with ID " + id + " deleted.";
    }
}

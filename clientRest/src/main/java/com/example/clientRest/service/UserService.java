package com.example.clientRest.service;

import com.example.clientRest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/users";

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // GET: Fetch all users
    public List<User> fetchUsers() {
        User[] users = restTemplate.getForObject(BASE_URL, User[].class);
        return Arrays.asList(users);
    }

    // POST: Add a new user
    public User addUser(User user) {
        return restTemplate.postForObject(BASE_URL, user, User.class);
    }

    // PUT: Update an existing user
    public User updateUser(int id, User updatedUser) {
        restTemplate.put(BASE_URL + "/" + id, updatedUser);
        return updatedUser;
    }

    // DELETE: Remove a user
    public String deleteUser(int id) {
        restTemplate.delete(BASE_URL + "/" + id);
        return "User with ID " + id + " deleted.";
    }

}

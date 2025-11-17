package com.example.EnglishApp.service;

import com.example.EnglishApp.model.User;
import com.example.EnglishApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public  User registerUser(User user){
        return  userRepository.save(user);
    }
    public List<User> getAllUser(){
        return  userRepository.findAll();
    }

    public User findByUsername(String username) {
        return  userRepository.findByUsername(username);
    }
    public  void deleteUser(long id){
        userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}

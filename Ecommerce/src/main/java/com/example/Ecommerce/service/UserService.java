package com.example.Ecommerce.service;


import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public  User loginUser(String email,String password){
        User user=userRepository.findByEmail(email);
        if(user!=null && user.getPassword().equals(password)){
            return  user;
        }
        return  null;
    }


}

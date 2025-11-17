package com.example.wishBirthDaySB.controller;


import com.example.wishBirthDaySB.model.User;
import com.example.wishBirthDaySB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @PostMapping
//    public User addUser(@RequestBody User user) {
//        return userRepository.save(user);
//    }

    @PostMapping("/new")
    public  String cre(@ModelAttribute User user,Model model){
        userRepository.save(user);
        model.addAttribute("val","Added");
        model.addAttribute("obj", new User());
        return "success";
    }

    @GetMapping("/show")
    public  String sho(Model model){
        System.out.println(userRepository.findAll());
        model.addAttribute("users",userRepository.findAll());
        return  "userlist";
    }

//    @GetMapping
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
    @GetMapping("/addpannu")
    public  String  open(Model model){
        model.addAttribute("obj",new User());
        return  "index";
    }
    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable Long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        model.addAttribute("obj", user.get());
        return "index";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id,Model model) {
        userRepository.deleteById(id);
        model.addAttribute("val","Deleted");
        return "success";
    }


}

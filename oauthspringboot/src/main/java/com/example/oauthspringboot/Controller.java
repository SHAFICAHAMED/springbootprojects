package com.example.oauthspringboot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Controller {
    @RequestMapping("/")
    public  String home(){
        return  "home";
    }
    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }

}

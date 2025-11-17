package com.example.cafemangement.restImpl;

import com.example.cafemangement.constents.CafeConstants;
import com.example.cafemangement.rest.UserRest;
import com.example.cafemangement.service.UserService;
import com.example.cafemangement.serviceImpl.UserServiceImpl;
import com.example.cafemangement.utils.CafeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class UserRestImpl implements UserRest {
    public UserService userService;

    public UserRestImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            userService.signUp(requestMap);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity("Succesfully register--",HttpStatus.OK);
    }
}

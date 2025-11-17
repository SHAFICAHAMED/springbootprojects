package com.example.securejwt.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/democonto")
public class demoControl {
    @GetMapping("/")
    public ResponseEntity<String> sayhell()
    {
        return ResponseEntity.ok("hi from secure EndPoinds");
    }
}

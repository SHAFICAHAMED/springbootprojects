package com.example.oauthspringboot;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ViewController {

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken authToken) {
            OAuth2User oAuth2User = authToken.getPrincipal();
            model.addAttribute("user", oAuth2User.getAttributes());
        }
        return "profile";
    }

}

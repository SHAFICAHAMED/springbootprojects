package com.example.oauthspringboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws  Exception{
        return httpSecurity.authorizeHttpRequests(registry->
        {
           registry.requestMatchers("/").permitAll();
           registry.anyRequest().authenticated();
        }) .oauth2Login(oauth -> oauth
                        .defaultSuccessUrl("/profile", true) // ✅ After login, go to profile
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // Redirect to home after logout
                        .permitAll()
                ).formLogin(Customizer.withDefaults())
        .build();
    }
}

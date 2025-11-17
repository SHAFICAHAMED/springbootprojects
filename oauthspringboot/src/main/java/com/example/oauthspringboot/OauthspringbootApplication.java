package com.example.oauthspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class OauthspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthspringbootApplication.class, args);
	}

}

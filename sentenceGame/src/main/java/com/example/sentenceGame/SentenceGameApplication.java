package com.example.sentenceGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SentenceGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentenceGameApplication.class, args);
	}

}

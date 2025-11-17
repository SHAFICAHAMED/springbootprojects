package com.example.catchinSB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CatchinSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatchinSbApplication.class, args);
	}

}

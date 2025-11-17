package com.example.EmailSendSb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmailSendSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailSendSbApplication.class, args);
	}

}

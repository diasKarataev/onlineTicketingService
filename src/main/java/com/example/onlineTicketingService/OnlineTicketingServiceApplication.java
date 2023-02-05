package com.example.onlineTicketingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OnlineTicketingServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineTicketingServiceApplication.class, args);
	}
}

package com.vikram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "https://localhost:3000")
public class OnlineFoodOrderingWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodOrderingWebsiteApplication.class, args);
	}

}

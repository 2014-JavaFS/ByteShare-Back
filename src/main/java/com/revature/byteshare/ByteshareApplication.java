package com.revature.byteshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class ByteshareApplication {

	public static void main(String[] args) {
		SpringApplication.run(ByteshareApplication.class, args);
	}

}
package com.userauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserAuthAppApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to User Authentication Application");
		SpringApplication.run(UserAuthAppApplication.class, args);
	}

}

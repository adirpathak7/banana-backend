package com.it.bananachipsbackend.bananachipsbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BananachipsbackendApplication {

	public static void main(String[] args) {

		System.out.println("Server is ready to start.");
		SpringApplication.run(BananachipsbackendApplication.class, args);
		System.out.println("Server started.");
	}

}

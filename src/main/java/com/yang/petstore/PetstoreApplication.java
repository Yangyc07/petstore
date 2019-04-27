package com.yang.petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PetstoreApplication {

	@RequestMapping("/")
	public String hello(){
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(PetstoreApplication.class, args);
	}

}

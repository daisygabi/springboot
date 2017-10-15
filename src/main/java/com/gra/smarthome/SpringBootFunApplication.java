package com.gra.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gra.smarthome.controllers", "com.gra.smarthome.services",
		"com.gra.smarthome.persistence", "com.gra.smarthome.persistence"})
public class SpringBootFunApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFunApplication.class, args);
	}
}
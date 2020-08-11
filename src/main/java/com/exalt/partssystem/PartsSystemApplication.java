package com.exalt.partssystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PartsSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartsSystemApplication.class, args);
	}

}

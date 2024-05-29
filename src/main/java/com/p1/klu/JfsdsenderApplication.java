package com.p1.klu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JfsdsenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JfsdsenderApplication.class, args);
		System.out.println("Sender Started");
	}

}

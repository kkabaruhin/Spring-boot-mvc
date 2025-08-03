package com.example.spring_user_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class SpringUserWebApplication {
	public static void main(String[] args) {

		SpringApplication.run(SpringUserWebApplication.class, args);
	}

}

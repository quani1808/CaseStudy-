package com.APIGatewayNetflix.APIGatewayNetflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class ApiGatewayNetflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayNetflixApplication.class, args);
	}

}
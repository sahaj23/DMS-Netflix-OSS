package com.techprimers.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
public class DmsSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmsSearchApplication.class, args);
	}
	
	@Configuration
	class Config {

		@LoadBalanced
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}

	}
}



package com.techprimers.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DmsReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmsReportApplication.class, args);
	}
}


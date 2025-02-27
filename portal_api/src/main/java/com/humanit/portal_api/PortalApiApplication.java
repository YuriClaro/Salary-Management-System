package com.humanit.portal_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PortalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApiApplication.class, args);
	}

}

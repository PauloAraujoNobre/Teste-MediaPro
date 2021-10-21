package com.media.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ProApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProApplication.class, args);
		
	}

}

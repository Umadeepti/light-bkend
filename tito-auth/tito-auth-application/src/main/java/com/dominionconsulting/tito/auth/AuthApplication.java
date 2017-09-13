package com.dominionconsulting.tito.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/security/securityContext.xml")
public class AuthApplication {
	//test
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}

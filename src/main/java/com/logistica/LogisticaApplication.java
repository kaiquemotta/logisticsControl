package com.logistica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class LogisticaApplication   {
//http://localhost:8080/swagger-ui.html
	public static void main(String[] args) {
		SpringApplication.run(LogisticaApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
}

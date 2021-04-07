package com.home.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:app.properties")
@ComponentScan(basePackages = "com") 
public class WebApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(WebApp1Application.class, args);
	}

}

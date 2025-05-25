package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example","com.controller","com.dao","com.model","com.service","com.exceptiondemo"})
@EntityScan("com.model")
@EnableJpaRepositories(basePackages="com.dao")
public class ProjectspringbootEventManagement1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjectspringbootEventManagement1Application.class, args);
	}

}

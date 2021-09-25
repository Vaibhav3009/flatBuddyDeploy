package com.flatbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import services.UserService;

@SpringBootApplication
@EntityScan(basePackages = {"model"})
@ComponentScan({"controller","services"})
@EnableJpaRepositories(basePackages = {"repository"})

public class FlatbuddyBackendApplication {


	public static void main(String[] args) {
		SpringApplication.run(FlatbuddyBackendApplication.class, args);
	}

}

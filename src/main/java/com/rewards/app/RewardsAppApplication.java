package com.rewards.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Rewards API", version = "1.0", description = "Info of reward apis"),
		servers = @Server(url = "http://localhost:8082", description = "Server details of reward apis")
		)// http://localhost:8082 can be accessed from http://localhost:8082/swagger-ui/index.html and docs from http://localhost:8082/v3/api-docs
public class RewardsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsAppApplication.class, args);
	}

}

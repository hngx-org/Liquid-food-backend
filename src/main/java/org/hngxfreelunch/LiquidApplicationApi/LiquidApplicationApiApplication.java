package org.hngxfreelunch.LiquidApplicationApi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Slf4j
@OpenAPIDefinition(
		info =
		@io.swagger.v3.oas.annotations.info.Info(
				description = "This app provides free lunch for workers in an organization",
				title = "Free Lunch App",
				version = "1.0"
		),
		servers = {
				@Server(
						url = "http://localhost:8000",
						description = "DEV Server"
				),
				@Server(
						url = "https://liquid-food-backend-production.up.railway.app",
						description = "PROD server"
				)
		},
		security = {
				@io.swagger.v3.oas.annotations.security.SecurityRequirement(
						name = "Bearer Authentication")
		})
@io.swagger.v3.oas.annotations.security.SecurityScheme(
		name = "Bearer Authentication",
		description = "JWT Authentication",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)

@EnableAsync
public class LiquidApplicationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiquidApplicationApiApplication.class, args);
		log.info("::::::Server Running::::::");
	}

}

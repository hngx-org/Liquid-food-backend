package org.hngxfreelunch.LiquidApplicationApi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Slf4j
@OpenAPIDefinition(
		info = @Info(
				title = "Free Lunch App",
				version = "v1",
				description = "This app provides free lunch for workers in an organization"
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
		externalDocs = @ExternalDocumentation(
				url = "",
				description = "Postman Documentation"
		)
)
@EnableAsync
public class LiquidApplicationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiquidApplicationApiApplication.class, args);
		log.info("::::::Server Running::::::");
	}

}

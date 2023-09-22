package org.hngxfreelunch.LiquidApplicationApi.utils;

import io.swagger.annotations.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
//                .info(new Info().title("My API").description("This is my API").version("1.0"))
                .components(new Components().addSecuritySchemes("bearer-auth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer")));
    }
}

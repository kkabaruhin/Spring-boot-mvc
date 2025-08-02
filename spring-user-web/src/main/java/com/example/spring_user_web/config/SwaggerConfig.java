package com.example.spring_user_web.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(
                        List.of(new Server().url("http://localhost:8080/ui/users"))
                ).info(
                        new Info().title("Spring app API")
                );
    }

}

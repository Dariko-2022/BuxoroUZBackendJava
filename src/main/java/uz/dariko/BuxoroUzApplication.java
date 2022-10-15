package uz.dariko;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import uz.dariko.properties.OpenApiProperties;
import uz.dariko.properties.ServiceProperties;

import java.util.Arrays;

@OpenAPIDefinition
@SpringBootApplication
@EnableConfigurationProperties({OpenApiProperties.class, ServiceProperties.class})

public class BuxoroUzApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuxoroUzApplication.class, args);
    }


}

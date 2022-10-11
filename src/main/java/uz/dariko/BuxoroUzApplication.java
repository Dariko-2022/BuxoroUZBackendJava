package uz.dariko;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.dariko.properties.OpenApiProperties;
import uz.dariko.properties.ServiceProperties;
@OpenAPIDefinition

@SpringBootApplication
@EnableConfigurationProperties({OpenApiProperties.class, ServiceProperties.class})

public class BuxoroUzApplication {

    public static void main(String[] args) {

        SpringApplication.run(BuxoroUzApplication.class, args);
    }

}

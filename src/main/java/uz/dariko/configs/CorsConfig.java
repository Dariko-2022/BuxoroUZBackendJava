package uz.dariko.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    // origin IP olmaydi.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowCredentials(true).
                allowedOrigins("http://localhost:3000/").
                allowedOrigins("http://localhost:3001/").
                allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH").
                allowedHeaders("*")
                .maxAge(18000);
    }
}

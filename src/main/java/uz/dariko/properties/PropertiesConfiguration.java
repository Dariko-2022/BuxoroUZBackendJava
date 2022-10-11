package uz.dariko.properties;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({OpenApiProperties.class, ServiceProperties.class})
@RequiredArgsConstructor
public class PropertiesConfiguration {
    private final OpenApiProperties openApiProperties;
    private final ServiceProperties serviceProperties;
}

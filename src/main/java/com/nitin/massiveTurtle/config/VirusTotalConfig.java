package com.nitin.massiveTurtle.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "massive-turtle.source.virus-total")
@Data
public class VirusTotalConfig {
    @Value("api-key")
    private String apiKey;
}

package com.nitin.massiveTurtle.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "massive-turtle.source.virus-total")
@Data
public class VirusTotalConfig {

    /*
        API key to be used for making calls to virus
        total APIs
     */
    @Value("api-key")
    private String apiKey;
}

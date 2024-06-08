package com.api.fruit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("com.application")
public class CustomProperties {
    public String apiUrl;

    public String docUrl;
}

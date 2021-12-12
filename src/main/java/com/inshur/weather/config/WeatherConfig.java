package com.inshur.weather.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "weather.api")
@Data
@RequiredArgsConstructor
public class WeatherConfig {

    private String key;
    private String url;
}

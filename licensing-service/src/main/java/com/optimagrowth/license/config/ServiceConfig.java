package com.optimagrowth.license.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Artyom Zheltyshev on 18.07.2023
 */
@ConfigurationProperties(prefix = "example")
@Configuration
@RefreshScope
@Getter
@Setter
public class ServiceConfig {

    private String property;

    @Value("${redis.server}")
    private String redisServer="";

    @Value("${redis.port}")
    private String redisPort="";

}

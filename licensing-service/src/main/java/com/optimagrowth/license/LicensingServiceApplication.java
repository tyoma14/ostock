package com.optimagrowth.license;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.events.model.OrganizationChangeModel;
import com.optimagrowth.license.utils.UserContextInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
@RefreshScope
@Slf4j
@EnableRedisRepositories
public class LicensingServiceApplication {

    @Autowired
    private ServiceConfig serviceConfig;

    public static void main(String[] args) {
        SpringApplication.run(LicensingServiceApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        template.getInterceptors()
                .add(new UserContextInterceptor());
        return template;
    }

    @Bean
    public Consumer<OrganizationChangeModel> loggerSink() {
        return orgChange -> log.debug(
                "Received an {} event for organization id {}",
                orgChange.getAction(),
                orgChange.getOrganizationId()
        );
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        String hostname = serviceConfig.getRedisServer();
        int port = Integer.parseInt(serviceConfig.getRedisPort());
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostname, port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

}

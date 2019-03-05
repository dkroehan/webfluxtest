package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Security configuration.
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        LOGGER.info("SecurityConfiguration being loaded.");
        return http
                .csrf().disable()
                .authorizeExchange()
                .anyExchange().permitAll()
                .and()
                .build();
    }

}

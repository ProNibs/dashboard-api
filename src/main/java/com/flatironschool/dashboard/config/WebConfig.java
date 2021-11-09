package com.flatironschool.dashboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${FRONTEND_HOSTNAME:localhost}")
    private String frontend_hostname;
    @Value("${FRONTEND_PORT:3000}")
    private String frontend_port;
    

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**")
            .allowedOrigins("http://"+frontend_hostname+":"+frontend_port);
    }
}
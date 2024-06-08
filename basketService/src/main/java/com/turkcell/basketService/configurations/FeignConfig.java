package com.turkcell.basketService.configurations;

import com.turkcell.corepackage.jwt.JwtService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Configuration
public class FeignConfig {

    @Autowired
    private JwtService jwtService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.isAuthenticated()) {
                    String jwtToken = jwtService.generateToken(authentication.getName(), List.of("admin")); // Kullanıcı rolleri burada alınmalı
                    requestTemplate.header("Authorization", "Bearer " + jwtToken);
                }
            }
        };
    }
}

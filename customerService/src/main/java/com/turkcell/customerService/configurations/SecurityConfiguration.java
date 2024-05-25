package com.turkcell.customerService.configurations;

import com.turkcell.corepackage.configuration.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final BaseSecurityService baseSecurityService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        baseSecurityService.configureCoreSecurity(http);
        http
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/customerservice/api/v1/individualcustomers/customerClient/{id}").permitAll()
                        .requestMatchers("/customerservice/api/v1/address/addressClient/{id}").permitAll()
                        .requestMatchers("/customerservice/api/**").hasAnyAuthority("admin")
                        .anyRequest().authenticated()
                );
        return http.build();
    }

}

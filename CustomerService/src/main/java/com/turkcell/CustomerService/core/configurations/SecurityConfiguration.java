package com.turkcell.CustomerService.core.configurations;

import com.turkcell.corepackage.configuration.BaseSecurityService;
import jakarta.ws.rs.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
//                        .requestMatchers(HttpMethod.GET, "/customerservice/api/v1/individualcustomers/").hasAnyAuthority("admin")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/v1/test/**").hasAnyAuthority("admin")
                        .requestMatchers("/customerservice/api/**").hasAnyAuthority("admin")
                        .anyRequest().authenticated()
                );
        return http.build();
    }

}

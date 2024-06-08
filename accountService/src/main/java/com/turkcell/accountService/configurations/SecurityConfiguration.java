package com.turkcell.accountService.configurations;

import com.turkcell.corepackage.configuration.BaseSecurityService;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        baseSecurityService.configureCoreSecurity(http);
        http
                .authorizeHttpRequests(req -> req
//                        .requestMatchers("/accountservice/api/v1/account/{id}").permitAll()
//                        .requestMatchers("/accountservice/api/v1/account/accountClient/{id}").permitAll()
                        .requestMatchers("/accountservice/api/**").hasAnyAuthority("admin")
                        .anyRequest().authenticated()
                );
        return http.build();
    }


}

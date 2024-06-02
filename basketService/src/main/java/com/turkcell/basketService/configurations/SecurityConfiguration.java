package com.turkcell.basketService.configurations;

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
                        .requestMatchers(HttpMethod.GET,"basketservice/api/v1/basket/{basketId}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"basketservice/api/v1/basket/{basketId}").permitAll()
                        .requestMatchers("/basketservice/api/**").hasAnyAuthority("admin")
                        .anyRequest().authenticated()
                );
        return http.build();
    }


}

package com.turkcell.identityService.business.security;

import com.turkcell.identityService.core.services.SecurityService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService
{
    private static final String[] WHITE_LIST_URLS = {
            "/identityService/api/v1/users",
            "/identityService/api/v1/auth/login"
    };

    @Override
    public HttpSecurity configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(x-> x
                .requestMatchers(WHITE_LIST_URLS).permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
                //.requestMatchers(HttpMethod.POST).permitAll()
                //.requestMatchers(HttpMethod.POST, "/customerservice/api/v1/customers").hasAnyAuthority(Roles.ADMIN, Roles.MODERATOR)
                .anyRequest().authenticated()
        );
        return http;
    }
}

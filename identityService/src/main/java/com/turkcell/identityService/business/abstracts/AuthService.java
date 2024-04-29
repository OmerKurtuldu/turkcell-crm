package com.turkcell.identityService.business.abstracts;


import com.turkcell.identityService.business.dtos.requests.LoginRequest;

public interface AuthService {
    String login(LoginRequest request);
    String refreshToken(String refreshToken);
}

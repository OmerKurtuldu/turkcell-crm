package com.turkcell.identityService.business.abstracts;

import com.turkcell.identityService.business.dtos.requests.LoginRequest;
import com.turkcell.identityService.business.dtos.requests.RegisterRequest;

public interface AuthService {
    String login(LoginRequest request);
    void register(RegisterRequest request);
    void giveRole(Integer id, Integer roleId);
    void updateEmail(Integer id, String email);
}

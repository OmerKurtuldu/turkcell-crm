package com.turkcell.identityService.business.abstracts;

import com.turkcell.identityService.business.dtos.requests.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void add(RegisterRequest request);
}

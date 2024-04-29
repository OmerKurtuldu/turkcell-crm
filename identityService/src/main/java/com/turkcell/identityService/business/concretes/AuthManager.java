package com.turkcell.identityService.business.concretes;

import com.turkcell.identityService.business.abstracts.AuthService;
import com.turkcell.identityService.business.abstracts.RefreshTokenService;
import com.turkcell.identityService.business.abstracts.UserService;
import com.turkcell.identityService.business.dtos.requests.LoginRequest;
import com.turkcell.identityService.business.messages.AuthMessages;
import com.turkcell.identityService.core.services.JwtService;
import com.turkcell.identityService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.identityService.entities.concretes.RefreshToken;
import com.turkcell.identityService.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if(!authentication.isAuthenticated())
            throw new BusinessException(AuthMessages.LOGIN_FAILED);

        User user = userService.findByUsername(request.getEmail());
        String jwt = generateJwt(user);
        refreshTokenService.create(user);

        return jwt;
    }

    @Override
    public String refreshToken(String refreshToken) {
        RefreshToken token = refreshTokenService.verifyRefreshToken(refreshToken);
        User user = token.getUser();
        return generateJwt(user);
    }


    private String generateJwt(User user)
    {
        Map<String,Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("id",user.getId());
        return jwtService.generateToken(claims, user.getEmail());
    }
}
// 9:10
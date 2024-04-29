package com.turkcell.identityService.api.controllers;

import com.turkcell.identityService.business.abstracts.AuthService;
import com.turkcell.identityService.business.dtos.requests.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/identityService/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request, HttpServletResponse response)
    {
        // Http-Only Cookie => Client tarafından erişilmeyen, server tarafından set edilen cookie
        Cookie httpOnlyCookie = new Cookie("refreshToken","abc");
        httpOnlyCookie.setHttpOnly(true);
        httpOnlyCookie.setMaxAge(10 * 24 * 60 * 60); // 10 gün
        response.addCookie(httpOnlyCookie);

        return authService.login(request);
    }

    @PostMapping("/refresh")
    public String refreshToken(HttpServletRequest request)
    {
        String refreshToken = getCookie(request,"refreshToken");
        return authService.refreshToken(refreshToken);
    }

    private String getCookie(HttpServletRequest request, String key)
    {
        Cookie[] cookies = request.getCookies();
        String refreshToken="";
        if(cookies!=null)
        {
            for (Cookie cookie:
                    cookies) {
                if (key.equals(cookie.getName()))
                {
                    refreshToken = cookie.getValue();
                }
            }
        }
        return refreshToken;
    }
}

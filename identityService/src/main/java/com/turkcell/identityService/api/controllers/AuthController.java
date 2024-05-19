package com.turkcell.identityService.api.controllers;

import com.turkcell.identityService.business.abstracts.AuthService;
import com.turkcell.identityService.business.dtos.requests.LoginRequest;
import com.turkcell.identityService.business.dtos.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest request)
    {
        authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginRequest request)
    {
        return authService.login(request);
    }

    @PostMapping("/role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void giveRole(@PathVariable Integer id, @RequestParam Integer roleId)
    {
        authService.giveRole(id, roleId);
    }
    @PutMapping("/email/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmail(@PathVariable Integer id, @RequestParam String email)
    {
        authService.updateEmail(id, email);
    }
}

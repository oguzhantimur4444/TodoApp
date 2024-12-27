package com.oguzhantimur4444.todo_api.controller;

import com.oguzhantimur4444.todo_api.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        return authenticationService.register(username, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return authenticationService.login(username, password);
    }
}
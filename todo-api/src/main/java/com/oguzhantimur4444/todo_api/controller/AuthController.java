package com.oguzhantimur4444.todo_api.controller;

import com.oguzhantimur4444.todo_api.model.CheckUsernameRequest;
import com.oguzhantimur4444.todo_api.model.LoginRequest;
import com.oguzhantimur4444.todo_api.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/checkUsername")
    public boolean userExists(@RequestBody CheckUsernameRequest request) {
        return !authenticationService.isUsernameTaken(request.getUsername());
    }

    @PostMapping("/register")
    public String register(@RequestBody LoginRequest request) {
        return authenticationService.register(request.getUsername(), request.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authenticationService.login(request.getUsername(), request.getPassword());
    }
}
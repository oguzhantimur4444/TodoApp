package com.oguzhantimur4444.todo_api.controller;

import com.oguzhantimur4444.todo_api.model.request.AuthenticationRequest;
import com.oguzhantimur4444.todo_api.model.response.AuthenticationResponse;
import com.oguzhantimur4444.todo_api.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/register")
    public AuthenticationResponse register(@RequestBody AuthenticationRequest request){
        return authenticationService.register(request);
    }

    @PostMapping(path = "/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
        return authenticationService.authenticate(request);
    }
}

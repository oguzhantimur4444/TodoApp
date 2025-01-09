package com.oguzhantimur4444.todo_api.service;

import com.oguzhantimur4444.todo_api.model.request.AuthenticationRequest;
import com.oguzhantimur4444.todo_api.model.User;
import com.oguzhantimur4444.todo_api.model.response.AuthenticationResponse;
import com.oguzhantimur4444.todo_api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(AuthenticationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return new AuthenticationResponse(jwtService.generateJwtToken(user));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        return new AuthenticationResponse(jwtService.generateJwtToken(user));
    }
}

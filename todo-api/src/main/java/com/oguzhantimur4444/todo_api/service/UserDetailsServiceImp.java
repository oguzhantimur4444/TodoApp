package com.oguzhantimur4444.todo_api.service;

import com.oguzhantimur4444.todo_api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    UserRepository repository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}

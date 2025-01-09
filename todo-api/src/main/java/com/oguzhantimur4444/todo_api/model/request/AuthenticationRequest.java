package com.oguzhantimur4444.todo_api.model.request;

public class AuthenticationRequest {
    String username;
    String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) { this.password = password; }
}

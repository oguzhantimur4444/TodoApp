package com.oguzhantimur4444.todo_api.model.response;

public class AuthenticationResponse {

    String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

}

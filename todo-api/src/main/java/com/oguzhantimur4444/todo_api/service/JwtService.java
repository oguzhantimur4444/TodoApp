package com.oguzhantimur4444.todo_api.service;

import com.oguzhantimur4444.todo_api.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String extractUsername(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateJwtToken(String authToken, UserDetails user) {
        final String username = extractUsername(authToken);
        return (username.equals(user.getUsername()) && !isTokenExpired(authToken));
    }
    public boolean isTokenExpired(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
    public String generateJwtToken(User user) {
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(jwtSecret)
                .compact();
    }
}

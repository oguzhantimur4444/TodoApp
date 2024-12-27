package com.oguzhantimur4444.todo_api.compoment;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {

    private final SecretKey jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Daha güvenli bir key kullanın
    private final long jwtExpirationMs = 86400000; // 24 saat

    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(jwtSecret)
                .compact();
    }
}

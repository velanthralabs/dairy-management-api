package com.velanthralabs.dairy.core.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms}")
    private long jwtExpirationInMs;

    // 1. Generate the Token (Modern 0.12.x style)
    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        // Create the key once
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(userPrincipal.getUsername()) // No more 'setSubject'
                .claim("role", userPrincipal.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(key) // Algorithm is detected automatically
                .compact();
    }

    // 2. Get Username (Modern 0.12.x style)
    public String getUsernameFromJWT(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser() // parserBuilder() is now just parser()
                .verifyWith(key) // setSigningKey() is now verifyWith()
                .build()
                .parseSignedClaims(token) // parseClaimsJws() is now parseSignedClaims()
                .getPayload() // getBody() is now getPayload()
                .getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

            Jwts.parser()
                    .verifyWith(key) // Updated from setSigningKey()
                    .build()
                    .parseSignedClaims(authToken); // Updated from parseClaimsJws()

            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
}
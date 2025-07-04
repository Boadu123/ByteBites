package com.example.api_gateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "projectTrackerSecretKeyIsAnActiveDataThatNeedToBeUsed"; // 32 chars minimum

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public boolean validateToken(String token) {
        return (extractUsername(token) !=null  && !checkExpiration(token));
    }

    private String extractUsername(String token) {
        String username = extractClaims(token).getSubject();
        return username;
    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    public boolean checkExpiration(String token) {
        Claims claim = extractClaims(token);
        return claim.getExpiration().before(new Date());
    }
}


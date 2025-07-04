package com.example.order_service.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtService {

    private final String SECRET = "projectTrackerSecretKeyIsAnActiveDataThatNeedToBeUsed";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    private String extractUsername(String token) {
        String username = extractClaims(token).getSubject();
        return username;
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean checkExpiration(String token) {
        Claims claim = extractClaims(token);
        return claim.getExpiration().before(new Date());
    }

    public String getEmailFromJwt(String jwt) {
        return extractClaims(jwt).getSubject();
    }

    public String getRole(String jwt) {
        return extractClaims(jwt).get("roles", String.class);
    }

    public long getId(String jwt) {
        return extractClaims(jwt).get("userId", Long.class);
    }
}

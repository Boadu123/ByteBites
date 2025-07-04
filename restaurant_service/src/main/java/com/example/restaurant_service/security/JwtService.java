package com.example.restaurant_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    private final String SECRET = "projectTrackerSecretKeyIsAnActiveDataThatNeedToBeUsed";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    private String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e){
            throw new JwtException("Invalid JWT token" + e.getMessage());
        }

    }

    public boolean checkExpiration(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String getEmailFromJwt(String jwt) {

        return extractClaim(jwt, Claims::getSubject);
    }

    public String getRole(String jwt) {
        Claims claims = extractAllClaims(jwt);
        return String.valueOf(claims.get("roles"));
    }

    public long getId(String jwt) {
        Claims claims = extractAllClaims(jwt);
        return Long.parseLong(claims.get("userId").toString());
    }
}

package org.unibuc.reservationservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;

@Component
public class JwtUtil {
    private final String jwtSecret="sa4u0SjY2lQv2NGsD61y5Qfe304fURqfbRqkD4lke5w=";

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public List<String> extractAuthorities(String token) {
        return extractClaims(token).get("authorities", List.class);
    }
}

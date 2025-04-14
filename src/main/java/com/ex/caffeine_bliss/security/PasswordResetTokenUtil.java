package com.ex.caffeine_bliss.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class PasswordResetTokenUtil {
    @Value("${jwt.reset.secret}")
    private String jwtResetSecret;

    @Value("${jwt.reset.expiration}")
    private int jwtResetExpirationMs;

    private SecretKey resetKey;

    @PostConstruct
    public void initResetKey() {
        this.resetKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtResetSecret));
    }

    public String generateResetToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtResetExpirationMs))
                .signWith(resetKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromResetToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(resetKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateResetToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(resetKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.out.println("Invalid Reset Token: " + e.getMessage());
            return false;
        }
    }

}

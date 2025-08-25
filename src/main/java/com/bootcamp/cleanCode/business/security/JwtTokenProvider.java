package com.bootcamp.cleanCode.business.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationMs;

    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateToken(String subject) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                   .setSubject(subject)
                   .setIssuedAt(now)
                   .setExpiration(expiry)
                   .signWith(getKey())
                   .compact();
    }

        public String createToken(String email, List<String> roles) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles.stream().collect(Collectors.joining(",")))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getSubject(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(getKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            
        }
        return false;
    }
}

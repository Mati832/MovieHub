package org.example.moviehub.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY;
    private final Duration TOKEN_EXPIRATION_TIME;


    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
    }

    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        extraClaims.put("role", userDetails.getAuthorities());
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME.toMillis()))
                .signWith(SignatureAlgorithm.HS256, getSecretKey())
                .compact();
    }


    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    public JwtService(@Value("${moviehub.security.authentication.jwt.secret-key}") String secretKey,
                      @Value("${moviehub.security.authentication.jwt.token-expiration-time}")Duration tokenExpirationTime) {
        SECRET_KEY = secretKey;
        TOKEN_EXPIRATION_TIME = tokenExpirationTime;
    }


}

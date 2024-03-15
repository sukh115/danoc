package com.danoc.danoc.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtProvider {

    @Value("${secret-key}")
    private String secretKey;

    public String create(String username, Long userId) {
        Date expiredDate =Date.from(Instant.now().plus(1,ChronoUnit.HOURS));
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        
        String jwt = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expiredDate)
            .claim("userId", userId)
            .compact();
        log.debug("create토큰");
        return jwt;
    }
    public String extractUsername(String jwt) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
            .build()
            .parseClaimsJws(jwt)
            .getBody();
        log.debug("claims 값 확인:{}", claims);
        return claims.getSubject();
    }
    public Long extractUserId(String jwt) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
            .build()
            .parseClaimsJws(jwt)
            .getBody();
        log.debug("claims 값 확인:{}", claims);
        return claims.get("userId", Long.class);
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            log.error("Error occurred while validating JWT token", e);
            return false;
        }
    }
}
    
    // public String validate(String jwt) {

    //     String subject = null;
    //     Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        
    //     try {
    //         Claims claims = Jwts.parserBuilder()
    //             .setSigningKey(key)
    //             .build()
    //             .parseClaimsJws(jwt)
    //             .getBody();
    
    //         subject = claims.getSubject();
    //         Long userId = claims.get("userId", Long.class);
    
    //         // username userId가 모두 존재하는지 확인
    //         if (subject != null || userId != null) {
    //             log.debug("subject 확인 : {}", subject);
    //             log.debug("userId 확인: {}", userId);
    //             log.debug("토큰 유효");
    //             return subject; 
    //         } else {
    //             log.debug("토큰이 포함하지 않습니다 : subject or userId");
    //             return null;
    //         }
    //     }   
    //         catch (Exception e) {
    //         log.debug("예외 발생", e);
    //         return null;
    //     }
    // }
    



    // private void getSubject() {
        
    //     throw new UnsupportedOperationException("Unimplemented method 'getSubject'");
    // }

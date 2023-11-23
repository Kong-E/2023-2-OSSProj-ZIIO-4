package com.ziio.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import java.util.Date;
@Slf4j
@Component
public class JwtUtil {
    @Value("${secret.key}")
    private String secretKey;

    // Jwt 토큰을 발급하는 메소드
    public String generateToken(String subject, long expirationMillis) {
        log.info("Successfully issued JWT token.");
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Jwt 토큰을 검증하고, 사용자의 E-mail을 반환하는 메소드
    public String getEmailFromToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            log.info("Return User E-mail.");
            return body.getSubject(); 
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid token.");
            return null;
        }
    }

    // 응답 헤더에서 Jwt 토큰을 반환하는 메소드
    public String getJwtTokenFromHeader(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }

}

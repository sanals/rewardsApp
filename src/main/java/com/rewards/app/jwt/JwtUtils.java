package com.rewards.app.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpiryMS}")
    private int jwtExpiryMS;

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String authorizationPrefix = "Bearer ";
        if(bearerToken != null && bearerToken.startsWith(authorizationPrefix)){
            return bearerToken.substring(authorizationPrefix.length());
        }
        return null;// check if we need to throw error
    }

    public String generateTokenFromUsername(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
//                .issuedAt(new Date())
                .issuedAt(Date.from(Instant.now()))
//                .expiration(new Date(new Date().getTime()+jwtExpiry))
                .expiration(Date.from(Instant.now().plus(Duration.ofMillis(jwtExpiryMS))))
                .signWith(key())
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        // check if we can have common method to verify claims, since we are not throwing any exception in getUsernameFromJwtToken
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build().parseSignedClaims(token)
                .getPayload().getSubject();// check is we need to throw error when verification failed
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String token) {
        try{
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token);// check if we can have common method to verify claims, since we are not throwing any exception in getUsernameFromJwtToken
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: {}" + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: {}" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: {}" + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {}" + e.getMessage());
        }
        return false;
    }
}

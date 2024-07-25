package com.rewards.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${secret.key}")
    String secretKey;

//    public JwtService() throws NoSuchAlgorithmException {
//        this.secretKey = secretKeyGen();
//    }

    public String geenrate(String name) throws NoSuchAlgorithmException {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    private String secretKeyGen() throws NoSuchAlgorithmException {
        KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey1 = key.generateKey();
        return Base64.getEncoder().encodeToString(secretKey1.getEncoded());
    }

    private Key getKey() throws NoSuchAlgorithmException {
        byte[] b = Decoders.BASE64.decode(secretKey);//secretKey //secretKeyGen()
        return Keys.hmacShaKeyFor(b);
    }

//    public String extractUsername(String token) {
////        return extractClaims(token, Claims::getSubject);
//        return "";
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return false;
//    }
    public String extractUsername(String token) throws NoSuchAlgorithmException {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) throws NoSuchAlgorithmException {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws NoSuchAlgorithmException {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token, UserDetails userDetails) throws NoSuchAlgorithmException {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) throws NoSuchAlgorithmException {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) throws NoSuchAlgorithmException {
        return extractClaim(token, Claims::getExpiration);
    }

}

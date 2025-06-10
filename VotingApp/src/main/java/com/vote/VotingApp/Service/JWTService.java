package com.vote.VotingApp.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    public String generateToken(String voterEmail) {
        Map<String,Object> claims=new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(voterEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*3*60))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }
}

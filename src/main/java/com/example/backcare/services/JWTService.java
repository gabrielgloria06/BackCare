package com.example.backcare.services;

import java.time.Instant;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JWTService {
    private final JwtEncoder encoder;

    public JWTService(JwtEncoder encoder){
        this.encoder = encoder;
    }

    public String generateToken(String name, String email, String birthdate){
        Instant now = Instant.now();
        long expiry = 3600L;

        var claims =  JwtClaimsSet.builder()
        .issuer("backcare")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .subject(email)
        .claim("name", name)
        .claim("email", email)
        .claim("birthdate", birthdate)
        .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
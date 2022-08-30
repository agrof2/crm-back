package com.crm.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.token-assigment}")
    private String chaveAssinatura;

    public String generateToken(UserDetails user) {
        long expiration = Long.valueOf(this.expiration);
        String signatureKey = this.chaveAssinatura;
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(expiration);
        Date dateTimeExpiration = Date
                .from(dateTime
                        .atZone(ZoneId
                                .systemDefault())
                        .toInstant());
        HashMap<String, Object> claim = new HashMap<String, Object>();
        claim.put("userId", 1L);

        return Jwts
                .builder()
                .setSubject(String.valueOf(1L))
                .setExpiration(dateTimeExpiration)
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }

    public Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(this.chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);
        Date dateExpiration = claims.getExpiration();
        LocalDateTime date = dateExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return !LocalDateTime.now().isAfter(date);
    }

    public String getLoginUsuario(String token) throws ExpiredJwtException {
        return (String) getClaims(token).getSubject();
    }
}

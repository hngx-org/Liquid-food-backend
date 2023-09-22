package org.hngxfreelunch.LiquidApplicationApi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.utils.BeanConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${Jwt_Secret_Key}")
    private String jwtSecretKey;

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateAccessToken(Map<String, Object> claims, String email) {
        Date expiration = Date.from(Instant.now().plusSeconds(60 * 60));
        return Jwts.builder()
                .setIssuer(BeanConfig.ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .setSubject(email)
                .compact();
    }

    public String generateRefreshToken(String email) {
        Date refreshExpiration = Date.from(Instant.now().plusSeconds(3600 * 24));
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer(BeanConfig.ISSUER)
                .setExpiration(refreshExpiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .setSubject(email)
                .compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecretKey)
                    .parseClaimsJws(token);
            return true; // Token is signed
        } catch (SignatureException e) {
            return false; //unsigned token
        } catch (JwtException e) {
            return false; // invalid token
        }
    }
}

package org.hngxfreelunch.LiquidApplicationApi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.utils.BeanConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private String generateSecret(){
        return DatatypeConverter.printBase64Binary(new byte[512/8]);
    }

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(generateSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication){
        String userEmail = authentication.getName();
        Date expiration = Date.from(Instant.now().plusSeconds(60 * 60));
        return Jwts
                .builder()
                .setSubject(userEmail)
                .setIssuer(BeanConfig.ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateRefreshToken(Authentication authentication){
        String userEmail = authentication.getName();
        Date refreshExpiration = Date.from(Instant.now().plusSeconds(3600 * 24));
        return Jwts
                .builder()
                .setSubject(userEmail)
                .setIssuer(BeanConfig.ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(refreshExpiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).after(new Date());
    }
    public boolean isTokenValid(String token, UserDetails details){
        String username = extractUsername(token);
        return username.equals(details.getUsername()) && isTokenExpired(token);
    }
}

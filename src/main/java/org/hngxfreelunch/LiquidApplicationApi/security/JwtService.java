package org.hngxfreelunch.LiquidApplicationApi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.utils.DateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private String generateSecret(){
        return DatatypeConverter.printBase64Binary(new byte[512/2]);
    }
    private Key generateSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(generateSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(Authentication authentication){
        String userEmail = authentication.getName();

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(DateUtils.getExpirationDate(4320))
                .signWith(generateSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    public String generateRefreshToken(Authentication authentication){
        String userEmail = authentication.getName();

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(DateUtils.getExpirationDate(43200))
                .signWith(generateSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(generateSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private <T> T extractSingleClaim(String token, Function<Claims, T> claimResolver){
        Claims claims = extractClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractUserEmail(String token){
        return extractSingleClaim(token, Claims::getSubject);
    }
    private Date extractExpiration(String token){
        return extractSingleClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).after(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String userEmail = extractUserEmail(token);
        return userEmail.equals(userDetails.getUsername())
                && isTokenExpired(token);
    }
}

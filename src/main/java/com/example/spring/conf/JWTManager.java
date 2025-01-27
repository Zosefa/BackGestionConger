package com.example.spring.conf;

import com.example.spring.model.Users;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Component
public class JWTManager {
    private final Key key;
    private final int jwt_expiration_days;
    private final Set<String> tokenBlacklist = new HashSet<>();

    @Autowired
    public JWTManager(Dotenv dotenv){
        this.key = new SecretKeySpec(Base64.getDecoder().decode(dotenv.get("JWT_SECRET")),
                SignatureAlgorithm.HS256.getJcaName());
        this.jwt_expiration_days = Integer.parseInt(dotenv.get("JWT_EXPIRATION_DAYS"));
    }

    public String generateToken(Users users) {
        java.util.Date currentDate = new java.util.Date();
        return Jwts.builder()
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + dayToMs(this.jwt_expiration_days)))
                .claim("id", users.getId_user())
                .claim("email", users.getEmail())
                .claim("employee", users.getEmployee_id())
                .signWith(key)
                .compact();
    }

    public void destroyToken(String token) {
        tokenBlacklist.add(token);
    }

    public boolean isTokenDestroyed(String token) {
        return tokenBlacklist.contains(token);
    }

    public String getClaim(String token, String claim_name) {
        Claims claims = getClaims(token);
        Object claimValue = claims.get(claim_name);
        return claimValue != null ? claimValue.toString() : null;
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public void validateToken(String token) throws AuthenticationCredentialsNotFoundException {
        if (isTokenDestroyed(token)) {
            throw new AuthenticationCredentialsNotFoundException("Token inutilisable!");
        }
        try {
            Claims claims = getClaims(token);
            java.util.Date expirationDate = claims.getExpiration();
            if (expirationDate != null && expirationDate.before(new java.util.Date())) {
                throw new AuthenticationCredentialsNotFoundException("Token expired");
            }
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("Invalid token", ex.fillInStackTrace());
        }
    }

    private long dayToMs(int days) {
        return days * 24 * 60 * 60 * 1000L;
    }

}

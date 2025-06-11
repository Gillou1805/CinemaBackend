// src/main/java/ue1104/iramps/be/api_backend/Security/JwtUtil.java
package ue1104.iramps.be.api_backend.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	// Méthode générée par IA !!!
    private static final String SECRET = "taCleUltraSecreteTrèsLonguePourSecurite!!!"; // clé secrète (doit être longue)
    public static final long EXPIRATION_MS = 3_600_000;  // 1 heure ( rendu public)

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username, String role, Long userId) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)   //cle/valeurs
                .claim("userId", userId)                      // on ajoute l’ID ici
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256) //algo 
                .compact();
    }

    // expose la clé pour les filtres
    public Key getKey() {
        return key;
    }

    // extrait l’ID
    public Long extractUserId(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .get("userId", Long.class);
    }

    // extrait le prénom
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
    
    //extrait le role
    public String extractUserRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}

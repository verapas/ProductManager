package ch.csbe.productmanager.resources.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import ch.csbe.productmanager.resources.user.User;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * verantwortlich für die Erstellung und Verwaltung von JWTs (JSON Web Tokens).
 * Generiert Tokens basierend auf Benutzerdaten und stellt
 * schlüssel für die Token-Signatur bereit.
 */
@Service
public class TokenService {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Generiert ein JWT für den angegebenen Benutzer. Der Token enthält die E-Mail des Benutzers
     * als Betreff (subject) und seine Rolle als Claim.
     */
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", user.getRole())  // Falls du mehrere Rollen hast, passe dies entsprechend an
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Token für 10 Stunden gültig
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * Gibt den geheimen Schlüssel zurück, der zur Signatur von JWTs verwendet wird.
     */
    public SecretKey getSecretKey() {
        return SECRET_KEY;
    }
}
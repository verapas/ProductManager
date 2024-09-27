package ch.csbe.productmanager.resources.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import ch.csbe.productmanager.resources.user.User;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", user.getRole())  // Falls du mehrere Rollen hast, passe dies entsprechend an
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Token für 10 Stunden gültig
                .signWith(SECRET_KEY)
                .compact();
    }

    public SecretKey getSecretKey() {
        return SECRET_KEY;
    }
}
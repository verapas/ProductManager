package ch.csbe.productmanager.resources.security;

import ch.csbe.productmanager.resources.user.User;
import ch.csbe.productmanager.resources.user.UserService;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/**
 * Filterklasse, die JWTs aus eingehenden HTTP-Anfragen verarbeitet und die Authentifizierung durchführt.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    /**
     * Diese Methode wird für jede eingehende HTTP-Anfrage aufgerufen.
     * Sie extrahiert das JWT aus der Autorisierungs-Header, validiert es und setzt die Authentifizierung im SecurityContext.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String email = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            email = Jwts.parser().setSigningKey(tokenService.getSecretKey()).parseClaimsJws(jwt).getBody().getSubject(); // E-Mail oder Benutzername aus dem Token extrahieren
        }
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.findUserByEmail(email);
            if (user != null) {
                MyUserPrincipal userPrincipal = new MyUserPrincipal(user);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * Extrahiert den Benutzernamen oder die E-Mail aus einem gegebenen JWT.
     */
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(tokenService.getSecretKey()).parseClaimsJws(token).getBody().getSubject(); // E-Mail oder Benutzername aus dem Token extrahieren
    }
}

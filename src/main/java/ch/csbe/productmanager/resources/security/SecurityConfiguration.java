package ch.csbe.productmanager.resources.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * SecurityConfiguration konfiguriert die Sicherheitsmassnahmen der Anwendung.
 * Sie definiert, welche Endpunkte zugänglich sind und welche Authentifizierung erfordern,
 * sowie die Rollenbasierte Autorisierung.
 */
@Configuration
public class SecurityConfiguration {

    @Autowired
    @Lazy
    private JwtRequestFilter jwtRequestFilter;

    /**
     * Konfiguriert die Sicherheitsfilterkette, einschliesslich der CSRF-Einstellungen,
     * Zugriffsberechtigungen für verschiedene Endpunkte und des JWT-Filters.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers("/auth/login", "/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/products/**", "/categories/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/products/**", "/categories/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/products/**", "/categories/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/products/**", "/categories/**").hasRole("ADMIN")
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Definiert einen Bean für die Passwortverschlüsselung mit BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}




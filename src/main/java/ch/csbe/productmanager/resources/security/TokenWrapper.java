package ch.csbe.productmanager.resources.security;

import lombok.Data;

/**
 * Verpackt JSON Web Token als String.
 * Wird verwendet, um das generierte Token als Antwort Anfragen zu senden.
 */
@Data
public class TokenWrapper {
    private String token;
}

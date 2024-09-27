package ch.csbe.productmanager.resources.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@Schema(description = "DTO zum Erstellen eines neuen Benutzers")
public class UserCreateDto {

    @NotNull
    @Schema(description = "Benutzername, der für die Anmeldung verwendet wird", example = "pasvera1997")
    private String username;

    @NotNull
    @Schema(description = "Passwort des Benutzers", example = "P@ssw0rd123")
    private String password;

    @NotNull
    @Schema(description = "Rolle des Benutzers (z.B. ADMIN, USER)", example = "ADMIN")
    private String role;

    @NotNull
    @Email
    @Schema(description = "E-Mail-Adresse des Benutzers", example = "pasvera@example.com")
    private String email;
}

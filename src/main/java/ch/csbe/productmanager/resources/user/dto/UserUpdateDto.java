package ch.csbe.productmanager.resources.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@Schema(description = "DTO zum Aktualisieren eines bestehenden Benutzers")
public class UserUpdateDto {

    @NotNull
    @Schema(description = "Neuer Benutzername des Benutzers", example = "pasvera1997")
    private String username;

    @NotNull
    @Schema(description = "Neues Passwort des Benutzers", example = "NewP@ssw0rd123")
    private String password;

    @NotNull
    @Schema(description = "Neue Rolle des Benutzers (z.B. ADMIN, USER)", example = "USER")
    private String role;

    @NotNull
    @Schema(description = "E-Mail-Adresse des Benutzers", example = "example@example.com")
    private String email;
}

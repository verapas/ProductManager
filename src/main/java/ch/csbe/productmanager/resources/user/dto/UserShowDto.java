package ch.csbe.productmanager.resources.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@Schema(description = "DTO zur Anzeige einer einfachen Übersicht eines Benutzers")
public class UserShowDto {

    @NotNull
    @Schema(description = "Eindeutige ID des Benutzers", example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Benutzername des Benutzers", example = "pasvera1997")
    private String username;

    @NotNull
    @Schema(description = "Rolle des Benutzers (z.B. ADMIN, USER)", example = "USER")
    private String role;
}

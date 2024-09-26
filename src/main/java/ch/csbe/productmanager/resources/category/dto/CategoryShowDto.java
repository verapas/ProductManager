package ch.csbe.productmanager.resources.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO zur Anzeige einer einfachen Übersicht einer Kategorie")
@Data
public class CategoryShowDto {

    @Schema(description = "Eindeutige ID der Kategorie", example = "1")
    @NotNull
    private Long id;

    @Schema(description = "Name der kategorie", example = "Elektronik")
    @NotNull
    private String name;

    @Schema(description = "Gibt an, ob die Kategorie aktiv ist", example = "true")
    @NotNull
    private boolean active;
}

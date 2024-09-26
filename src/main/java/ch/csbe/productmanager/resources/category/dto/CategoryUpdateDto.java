package ch.csbe.productmanager.resources.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO zum Updaten einer Kategorie")
@Data
public class CategoryUpdateDto {

    @Schema(description = "Name der kategorie", example = "Elektronik")
    @NotNull
    private String name;

    @Schema(description = "Gibt an, ob die Kategorie aktiv ist", example = "true")
    @NotNull
    private boolean active;
}
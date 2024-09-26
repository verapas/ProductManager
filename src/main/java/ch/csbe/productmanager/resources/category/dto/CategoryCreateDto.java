package ch.csbe.productmanager.resources.category.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@Schema(description = "DTO zum erstellen einer neuen kategorie")
public class CategoryCreateDto {

    @Schema(description = "name der kategorie", example = "Elektronik")
    @NotNull
    private String name;

    @Schema(description = "Gibt an, ob die Kategorie aktiv ist", example = "true")
    @NotNull
    private boolean active;
}

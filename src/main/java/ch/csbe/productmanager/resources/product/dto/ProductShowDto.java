package ch.csbe.productmanager.resources.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO zur Anzeige einer einfach Übersicht eines Produktes")
@Data
public class ProductShowDto {

    @Schema(description = "Eindeutige ID eines Produktes", example = "1")
    @NotNull
    private Long id;

    @Schema(description = "Name des Produkts", example = "Laptop")
    @NotNull
    private String name;

    @Schema(description = "Preis des Produkts", example = "999.95")
    @NotNull
    private float price;


}

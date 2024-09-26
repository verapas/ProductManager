package ch.csbe.productmanager.resources.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductUpdateDto {
    @NotNull
    @Schema(description = "Name des Produkts", example = "Laptop")
    private String name;

    @NotNull
    @Schema(description = "SKU (Stock Keeping Unit) des Produkts, eindeutige Artikelnummer", example = "ABC123")
    private String sku;

    @NotNull
    @Schema(description = "Gibt an, ob das Produkt aktiv ist", example = "true")
    private boolean active;

    @Schema(description = "Bild-URL des Produkts", example = "http://example.com/image.jpg")
    private String image;

    @Schema(description = "Beschreibung des Produkts", example = "Ein leistungsstarker Laptop für den täglichen Gebrauch.")
    private String description;

    @NotNull
    @Schema(description = "Preis des Produkts", example = "999.95")
    private float price;

    @NotNull
    @Schema(description = "Verfügbare Stückzahl des Produkts im Lager", example = "50")
    private int stock;

    @NotNull
    @Schema(description = "ID der Kategorie, zu der das Produkt gehört", example = "1")
    private Long categoryId;
}

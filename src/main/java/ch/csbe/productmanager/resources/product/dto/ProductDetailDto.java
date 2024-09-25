package ch.csbe.productmanager.resources.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDetailDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String sku;
    @NotNull
    private boolean active;
    private String image;
    private String description;
    @NotNull
    private float price;
    @NotNull
    private int stock;
    @NotNull
    private String categoryName;
}

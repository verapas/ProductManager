package ch.csbe.productmanager.resources.category.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class CategoryShowDto {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private boolean active;
}

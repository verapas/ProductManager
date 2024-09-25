package ch.csbe.productmanager.resources.user.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class UserShowDto {
    @NotNull
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String role;
}

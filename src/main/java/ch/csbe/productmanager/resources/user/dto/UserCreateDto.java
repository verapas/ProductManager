package ch.csbe.productmanager.resources.user.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class UserCreateDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String role;
}

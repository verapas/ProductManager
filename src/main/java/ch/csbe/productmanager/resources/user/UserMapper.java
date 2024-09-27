package ch.csbe.productmanager.resources.user;

import ch.csbe.productmanager.resources.user.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "email", target = "email")  // Mapping für email hinzufügen
    UserShowDto toShowDto(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "email", target = "email")  // Mapping für email hinzufügen
    UserDetailDto toDetailDto(User user);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "email", target = "email")  // Mapping für email hinzufügen
    User toEntity(UserCreateDto userCreateDto);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "email", target = "email")  // Mapping für email hinzufügen
    void updateEntity(UserUpdateDto userUpdateDto, @MappingTarget User user);
}

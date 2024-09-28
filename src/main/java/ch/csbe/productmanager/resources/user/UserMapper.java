package ch.csbe.productmanager.resources.user;

import ch.csbe.productmanager.resources.user.dto.UserCreateDto;
import ch.csbe.productmanager.resources.user.dto.UserDetailDto;
import ch.csbe.productmanager.resources.user.dto.UserShowDto;
import ch.csbe.productmanager.resources.user.dto.UserUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


/**
 * Mapper-Klasse für die Umwandlung von Benutzer-Entitäten in DTOs und umgekehrt.
 * Diese Schnittstelle verwendet MapStruct, um automatisch Mapping-Implementierungen zu generieren.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "role", target = "role")
    UserShowDto toShowDto(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "role", target = "role")
    UserDetailDto toDetailDto(User user);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "email", target = "email")  // Stelle sicher, dass das Mapping für alle relevanten Felder vorhanden ist
    User toEntity(UserCreateDto userCreateDto);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "email", target = "email")
    void updateEntity(UserUpdateDto userUpdateDto, @MappingTarget User user);
}

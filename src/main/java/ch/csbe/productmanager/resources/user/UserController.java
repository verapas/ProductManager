package ch.csbe.productmanager.resources.user;

import ch.csbe.productmanager.resources.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@Tag(name = "UserController", description = "Verwaltet die CRUD-Operationen für Benutzer")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // URL: http://localhost:8080/users
    @GetMapping
    @Operation(summary = "Listet alle Benutzer auf", description = "Gibt eine Liste aller Benutzer zurück.")
    public List<UserShowDto> getAllUsers() {
        return userService.findAll();
    }

    // URL: http://localhost:8080/users/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Findet einen Benutzer nach ID", description = "Gibt die Details eines Benutzers anhand der spezifischen ID zurück.")
    public UserDetailDto getUserById(
            @Parameter(description = "Die ID des Benutzers, der abgerufen werden soll", example = "1")
            @PathVariable Long id) {
        return userService.findById(id);
    }

    // URL: http://localhost:8080/users
    @PostMapping
    @Operation(summary = "Erstellt einen neuen Benutzer", description = "Erstellt einen neuen Benutzer mit den angegebenen Daten.")
    public UserDetailDto createUser(
            @Parameter(description = "Daten des zu erstellenden Benutzers")
            @RequestBody UserCreateDto dto) {
        return userService.create(dto);
    }

    // URL: http://localhost:8080/users/{id}
    @PutMapping("/{id}")
    @Operation(summary = "Aktualisiert einen Benutzer", description = "Aktualisiert einen bestehenden Benutzer anhand der spezifischen ID.")
    public UserDetailDto updateUser(
            @Parameter(description = "Die ID des Benutzers, der aktualisiert werden soll", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Die neuen Daten für den Benutzer")
            @RequestBody UserUpdateDto dto) {
        return userService.update(id, dto);
    }

    // URL: http://localhost:8080/users/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "Löscht einen Benutzer", description = "Löscht einen Benutzer anhand der spezifischen ID.")
    public void deleteUser(
            @Parameter(description = "Die ID des Benutzers, der gelöscht werden soll", example = "1")
            @PathVariable Long id) {
        userService.delete(id);
    }
}

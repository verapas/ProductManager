package ch.csbe.productmanager.resources.user;

import ch.csbe.productmanager.resources.user.dto.*;
import ch.csbe.productmanager.resources.security.TokenService;
import ch.csbe.productmanager.resources.security.TokenWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * Controller für die Verwaltung der CRUD-Operationen für Benutzer.
 */
@RestController
@Tag(name = "UserController", description = "Verwaltet die CRUD-Operationen für Benutzer")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * Listet alle Benutzer auf.
     * URL: http://localhost:8080/users
     */
    @GetMapping
    @Operation(summary = "Listet alle Benutzer auf", description = "Gibt eine Liste aller Benutzer zurück.")
    @ApiResponse(responseCode = "200", description = "Erfolgreiches Abrufen der Benutzer")
    public ResponseEntity<List<UserShowDto>> getAllUsers() {
        List<UserShowDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
    /**
     * Findet einen Benutzer nach ID.
     * URL: http://localhost:8080/users/{id}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Findet einen Benutzer nach ID", description = "Gibt die Details eines Benutzers anhand der spezifischen ID zurück.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Benutzer gefunden und zurückgegeben"),
            @ApiResponse(responseCode = "404", description = "Benutzer nicht gefunden")
    })
    public ResponseEntity<UserDetailDto> getUserById(
            @Parameter(description = "Die ID des Benutzers, der abgerufen werden soll", example = "1")
            @PathVariable Long id) {
        UserDetailDto user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Erstellt einen neuen Benutzer.
     * URL: http://localhost:8080/users
     */
    @PostMapping
    @Operation(summary = "Erstellt einen neuen Benutzer", description = "Erstellt einen neuen Benutzer mit den angegebenen Daten.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Benutzer erfolgreich erstellt"),
            @ApiResponse(responseCode = "400", description = "Ungültige Anfrage")
    })
    public ResponseEntity<UserDetailDto> createUser(
            @Parameter(description = "Daten des zu erstellenden Benutzers")
            @RequestBody UserCreateDto dto) {
        UserDetailDto createdUser = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Aktualisiert einen bestehenden Benutzer.
     * URL: http://localhost:8080/users/{id}
     */
    @PutMapping("/{id}")
    @Operation(summary = "Aktualisiert einen Benutzer", description = "Aktualisiert einen bestehenden Benutzer anhand der spezifischen ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Benutzer erfolgreich aktualisiert"),
            @ApiResponse(responseCode = "404", description = "Benutzer nicht gefunden"),
            @ApiResponse(responseCode = "400", description = "Ungültige Anfrage")
    })
    public ResponseEntity<UserDetailDto> updateUser(
            @Parameter(description = "Die ID des Benutzers, der aktualisiert werden soll", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Die neuen Daten für den Benutzer")
            @RequestBody UserUpdateDto dto) {
        UserDetailDto updatedUser = userService.update(id, dto);
        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Löscht einen Benutzer.
     * URL: http://localhost:8080/users/{id}
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Löscht einen Benutzer", description = "Löscht einen Benutzer anhand der spezifischen ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Benutzer erfolgreich gelöscht"),
            @ApiResponse(responseCode = "404", description = "Benutzer nicht gefunden")
    })
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "Die ID des Benutzers, der gelöscht werden soll", example = "1")
            @PathVariable Long id) {
        UserDetailDto user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Authentifiziert den Benutzer und gibt ein JWT zurück.
     * URL: http://localhost:8080/users/login
     */
    @PostMapping("login")
    @Operation(summary = "Authentifiziert den Benutzer", description = "Überprüft die Anmeldedaten und gibt ein JWT zurück, wenn die Authentifizierung erfolgreich ist.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Anmeldung erfolgreich, Token zurückgegeben"),
            @ApiResponse(responseCode = "401", description = "Ungültige Anmeldedaten")
    })
    public ResponseEntity<TokenWrapper> login(
            @Parameter(description = "Anmeldedaten des Benutzers")
            @RequestBody LoginRequestDto loginRequestDto) {
        User user = this.userService.getUserWithCredentials(loginRequestDto);
        if (user != null) {
            TokenWrapper tokenWrapper = new TokenWrapper();
            String token = this.tokenService.generateToken(user);
            tokenWrapper.setToken(token);
            return ResponseEntity.ok(tokenWrapper);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}

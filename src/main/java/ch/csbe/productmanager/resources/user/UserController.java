package ch.csbe.productmanager.resources.user;

import ch.csbe.productmanager.resources.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public List<UserShowDto> getAllUsers() {
        return userService.findAll();
    }

    // URL: http://localhost:8080/users/{id}
    @GetMapping("/{id}")
    public UserDetailDto getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    // URL: http://localhost:8080/users
    @PostMapping
    public UserDetailDto createUser(@RequestBody UserCreateDto dto) {
        return userService.create(dto);
    }

    // URL: http://localhost:8080/users/{id}
    @PutMapping("/{id}")
    public UserDetailDto updateUser(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
        return userService.update(id, dto);
    }

    // URL: http://localhost:8080/users/{id}
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}

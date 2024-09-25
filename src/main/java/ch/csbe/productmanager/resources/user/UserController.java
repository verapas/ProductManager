package ch.csbe.productmanager.resources.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        return "User with ID: " + id;
    }

    @PostMapping
    public String createUser(@RequestBody String user) {
        return "User created: " + user;
    }

    @PutMapping("{id}")
    public String updateUser(@PathVariable int id, @RequestBody String user) {
        return "User with ID: " + id + " updated: " + user;
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable int id) {
        return "User with ID: " + id + " deleted";
    }
}

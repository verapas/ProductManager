package ch.csbe.productmanager.ressources.user;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        return "User mit ID: " + id;
    }

    @PostMapping
    public String createUser(@RequestBody String user) {
        return "User erstellt: " + user;
    }

    @PutMapping("{id}")
    public String updateUser(@PathVariable int id, @RequestBody String user) {
        return "User mit ID: " + id + "aktualisiert: " + user;
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable int id) {
        return "User mit ID: " + id + "gelöscht";
    }
}
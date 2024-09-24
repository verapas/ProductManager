package ch.csbe.productmanager.ressources.category;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping("/{id}")
    public String getCategory(@PathVariable int id) {
        return "Kategorie mit ID: " + id;
    }

    @PostMapping
    public String createCategory(@RequestBody String category) {
        return "Kategorie erstellt: " + category;
    }

    @PutMapping("{id}")
    public String updateCategory(@PathVariable int id, @RequestBody String category) {
        return "Kategorie mit ID: " + id + "aktualisiert: " + category;
    }

    @DeleteMapping("{id}")
    public String deleteCategory(@PathVariable int id) {
        return "Kategorie mit ID: " + id + "gelöscht";
    }
}
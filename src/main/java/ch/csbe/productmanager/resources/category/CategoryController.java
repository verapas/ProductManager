package ch.csbe.productmanager.resources.category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public String getCategory(@PathVariable int id) {
        return "Category with ID: " + id;
    }

    @PostMapping
    public String createCategory(@RequestBody String category) {
        return "Category created: " + category;
    }

    @PutMapping("{id}")
    public String updateCategory(@PathVariable int id, @RequestBody String category) {
        return "Category with ID: " + id + " updated: " + category;
    }

    @DeleteMapping("{id}")
    public String deleteCategory(@PathVariable int id) {
        return "Category with ID: " + id + " deleted";
    }
}

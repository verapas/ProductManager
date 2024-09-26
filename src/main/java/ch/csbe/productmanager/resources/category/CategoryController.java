package ch.csbe.productmanager.resources.category;

import ch.csbe.productmanager.resources.category.dto.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "CategoryController", description = "Verwaltet die CRUD-Operationen für die Kategorien")
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // URL: http://localhost:8080/categories
    @GetMapping
    public List<CategoryShowDto> getAllCategories() {
        return categoryService.findAll();
    }

    // URL: http://localhost:8080/categories/{id}
    @GetMapping("/{id}")
    public CategoryDetailDto getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    // URL: http://localhost:8080/categories
    @PostMapping
    public CategoryDetailDto createCategory(@RequestBody CategoryCreateDto dto) {
        return categoryService.create(dto);
    }

    // URL: http://localhost:8080/categories/{id}
    @PutMapping("/{id}")
    public CategoryDetailDto updateCategory(@PathVariable Long id, @RequestBody CategoryUpdateDto dto) {
        return categoryService.update(id, dto);
    }

    // URL: http://localhost:8080/categories/{id}
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}

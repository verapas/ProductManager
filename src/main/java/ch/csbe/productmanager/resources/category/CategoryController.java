package ch.csbe.productmanager.resources.category;

import ch.csbe.productmanager.resources.category.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryShowDto> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDetailDto getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public CategoryDetailDto createCategory(@RequestBody CategoryCreateDto dto) {
        return categoryService.create(dto);
    }

    @PutMapping("/{id}")
    public CategoryDetailDto updateCategory(@PathVariable Long id, @RequestBody CategoryUpdateDto dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
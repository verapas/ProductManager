package ch.csbe.productmanager.resources.category;

import ch.csbe.productmanager.resources.category.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "Listet alle Kategorien auf", description = "Gibt eine Liste aller Kategorien zurück.")
    public List<CategoryShowDto> getAllCategories() {
        return categoryService.findAll();
    }

    // URL: http://localhost:8080/categories/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Findet eine Kategorie nach ID", description = "Gibt die Details einer Kategorie anhand der spezifischen ID zurück.")
    public CategoryDetailDto getCategoryById(
            @Parameter(description = "Die ID der Kategorie, die abgerufen werden soll", example = "1")
            @PathVariable Long id) {
        return categoryService.findById(id);
    }

    // URL: http://localhost:8080/categories
    @PostMapping
    @Operation(summary = "Erstellt eine neue Kategorie", description = "Erstellt eine neue Kategorie mit den angegebenen Daten.")
    public CategoryDetailDto createCategory(
            @Parameter(description = "Daten der zu erstellenden Kategorie")
            @RequestBody CategoryCreateDto dto) {
        return categoryService.create(dto);
    }

    // URL: http://localhost:8080/categories/{id}
    @PutMapping("/{id}")
    @Operation(summary = "Aktualisiert eine Kategorie", description = "Aktualisiert eine bestehende Kategorie anhand der spezifischen ID.")
    public CategoryDetailDto updateCategory(
            @Parameter(description = "Die ID der Kategorie, die aktualisiert werden soll", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Die neuen Daten für die Kategorie")
            @RequestBody CategoryUpdateDto dto) {
        return categoryService.update(id, dto);
    }

    // URL: http://localhost:8080/categories/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "Löscht eine Kategorie", description = "Löscht eine Kategorie anhand der spezifischen ID.")
    public void deleteCategory(
            @Parameter(description = "Die ID der Kategorie, die gelöscht werden soll", example = "1")
            @PathVariable Long id) {
        categoryService.delete(id);
    }
}
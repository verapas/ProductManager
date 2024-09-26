package ch.csbe.productmanager.resources.category;

import ch.csbe.productmanager.resources.category.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ApiResponse(responseCode = "200", description = "Erfolgreiches Abrufen der Kategorien")
    public ResponseEntity<List<CategoryShowDto>> getAllCategories() {
        List<CategoryShowDto> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    // URL: http://localhost:8080/categories/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Findet eine Kategorie nach ID", description = "Gibt die Details einer Kategorie anhand der spezifischen ID zurück.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Kategorie gefunden und zurückgegeben"),
            @ApiResponse(responseCode = "404", description = "Kategorie nicht gefunden")
    })

    public ResponseEntity<CategoryDetailDto> getCategoryById(
            @Parameter(description = "Die ID der Kategorie, die abgerufen werden soll", example = "1")
            @PathVariable Long id) {
        CategoryDetailDto category = categoryService.findById(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(category);
    }

    // URL: http://localhost:8080/categories
    @PostMapping
    @Operation(summary = "Erstellt eine neue Kategorie", description = "Erstellt eine neue Kategorie mit den angegebenen Daten.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Kategorie erfolgreich erstellt"),
            @ApiResponse(responseCode = "400", description = "Ungültige Anfrage")
    })
    public ResponseEntity<CategoryDetailDto> createCategory(
            @Parameter(description = "Daten der zu erstellenden Kategorie")
            @RequestBody CategoryCreateDto dto) {
        CategoryDetailDto createdCategory = categoryService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    // URL: http://localhost:8080/categories/{id}
    @PutMapping("/{id}")
    @Operation(summary = "Aktualisiert eine Kategorie", description = "Aktualisiert eine bestehende Kategorie anhand der spezifischen ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Kategorie erfolgreich aktualisiert"),
            @ApiResponse(responseCode = "404", description = "Kategorie nicht gefunden"),
            @ApiResponse(responseCode = "400", description = "Ungültige Anfrage")
    })
    public ResponseEntity<CategoryDetailDto> updateCategory(
            @Parameter(description = "Die ID der Kategorie, die aktualisiert werden soll", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Die neuen Daten für die Kategorie")
            @RequestBody CategoryUpdateDto dto) {
        CategoryDetailDto updatedCategory = categoryService.update(id, dto);
        if (updatedCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedCategory);
    }

    // URL: http://localhost:8080/categories/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "Löscht eine Kategorie", description = "Löscht eine Kategorie anhand der spezifischen ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Kategorie erfolgreich gelöscht"),
            @ApiResponse(responseCode = "404", description = "Kategorie nicht gefunden")
    })
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = "Die ID der Kategorie, die gelöscht werden soll", example = "1")
            @PathVariable Long id) {
        CategoryDetailDto category = categoryService.findById(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

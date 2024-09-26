package ch.csbe.productmanager.resources.product;

import ch.csbe.productmanager.resources.product.dto.ProductCreateDto;
import ch.csbe.productmanager.resources.product.dto.ProductShowDto;
import ch.csbe.productmanager.resources.product.dto.ProductUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@Tag(name = "ProductController", description = "Verwaltet die CRUD-Operationen für Produkte")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // URL: http://localhost:8080/products
    @GetMapping
    @Operation(summary = "Listet alle Produkte auf", description = "Gibt eine Liste aller Produkte zurück. Optional kann nach Kategorie gefiltert werden.")
    public List<ProductShowDto> getAllProducts(
            @Parameter(description = "Kategorie, nach der gefiltert werden soll", example = "Drucker")
            @RequestParam(required = false) String filterByCategory) {
        return productService.findAll(filterByCategory);
    }

    // URL: http://localhost:8080/products/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Findet ein Produkt nach ID", description = "Gibt die Details eines Produkts anhand der spezifischen ID zurück.")
    public ProductShowDto getProductById(
            @Parameter(description = "Die ID des Produkts, das abgerufen werden soll", example = "1")
            @PathVariable Long id) {
        return productService.findById(id);
    }

    // URL: http://localhost:8080/products
    @PostMapping
    @Operation(summary = "Erstellt ein neues Produkt", description = "Erstellt ein neues Produkt mit den angegebenen Daten.")
    public ProductShowDto createProduct(
            @Parameter(description = "Daten des zu erstellenden Produkts")
            @RequestBody ProductCreateDto dto) {
        return productService.create(dto);
    }

    // URL: http://localhost:8080/products/{id}
    @PutMapping("/{id}")
    @Operation(summary = "Aktualisiert ein Produkt", description = "Aktualisiert ein bestehendes Produkt anhand der spezifischen ID.")
    public ProductShowDto updateProduct(
            @Parameter(description = "Die ID des Produkts, das aktualisiert werden soll", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Die neuen Daten für das Produkt")
            @RequestBody ProductUpdateDto dto) {
        return productService.update(id, dto);
    }

    // URL: http://localhost:8080/products/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "Löscht ein Produkt", description = "Löscht ein Produkt anhand der spezifischen ID.")
    public void deleteProduct(
            @Parameter(description = "Die ID des Produkts, das gelöscht werden soll", example = "1")
            @PathVariable Long id) {
        productService.delete(id);
    }
}
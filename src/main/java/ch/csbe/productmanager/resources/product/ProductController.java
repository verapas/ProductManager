package ch.csbe.productmanager.resources.product;

import ch.csbe.productmanager.resources.product.dto.ProductCreateDto;
import ch.csbe.productmanager.resources.product.dto.ProductShowDto;
import ch.csbe.productmanager.resources.product.dto.ProductUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * Controller für die Verwaltung der CRUD-Operationen für Produkte.
 */
@RestController
@Tag(name = "ProductController", description = "Verwaltet die CRUD-Operationen für Produkte")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Listet alle Produkte auf, optional gefiltert nach einer Kategorie.
     */
    @GetMapping
    @Operation(summary = "Listet alle Produkte auf", description = "Gibt eine Liste aller Produkte zurück. Optional kann nach Kategorie gefiltert werden.")
    @ApiResponse(responseCode = "200", description = "Erfolgreiches Abrufen der Produkte")
    public ResponseEntity<List<ProductShowDto>> getAllProducts(
            @Parameter(description = "Kategorie, nach der gefiltert werden soll", example = "Drucker")
            @RequestParam(required = false) String filterByCategory) {
        List<ProductShowDto> products = productService.findAll(filterByCategory);
        return ResponseEntity.ok(products);
    }

    /**
     * Findet ein Produkt nach seiner ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Findet ein Produkt nach ID", description = "Gibt die Details eines Produkts anhand der spezifischen ID zurück.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produkt gefunden und zurückgegeben"),
            @ApiResponse(responseCode = "404", description = "Produkt nicht gefunden")
    })
    public ResponseEntity<ProductShowDto> getProductById(
            @Parameter(description = "Die ID des Produkts, das abgerufen werden soll", example = "1")
            @PathVariable Long id) {
        ProductShowDto product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(product);
    }

    /**
     * Erstellt ein neues Produkt mit den angegebenen Daten.
     */
    @PostMapping
    @Operation(summary = "Erstellt ein neues Produkt", description = "Erstellt ein neues Produkt mit den angegebenen Daten.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produkt erfolgreich erstellt"),
            @ApiResponse(responseCode = "400", description = "Ungültige Anfrage")
    })
    public ResponseEntity<ProductShowDto> createProduct(
            @Parameter(description = "Daten des zu erstellenden Produkts")
            @RequestBody ProductCreateDto dto) {
        ProductShowDto createdProduct = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Aktualisiert ein bestehendes Produkt anhand der spezifischen ID.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Aktualisiert ein Produkt", description = "Aktualisiert ein bestehendes Produkt anhand der spezifischen ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produkt erfolgreich aktualisiert"),
            @ApiResponse(responseCode = "404", description = "Produkt nicht gefunden"),
            @ApiResponse(responseCode = "400", description = "Ungültige Anfrage")
    })
    public ResponseEntity<ProductShowDto> updateProduct(
            @Parameter(description = "Die ID des Produkts, das aktualisiert werden soll", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Die neuen Daten für das Produkt")
            @RequestBody ProductUpdateDto dto) {
        ProductShowDto updatedProduct = productService.update(id, dto);
        if (updatedProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Löscht ein Produkt anhand der spezifischen ID.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Löscht ein Produkt", description = "Löscht ein Produkt anhand der spezifischen ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produkt erfolgreich gelöscht"),
            @ApiResponse(responseCode = "404", description = "Produkt nicht gefunden")
    })
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "Die ID des Produkts, das gelöscht werden soll", example = "1")
            @PathVariable Long id) {
        ProductShowDto product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package ch.csbe.productmanager.resources.product;

import ch.csbe.productmanager.resources.product.dto.ProductCreateDto;
import ch.csbe.productmanager.resources.product.dto.ProductShowDto;
import ch.csbe.productmanager.resources.product.dto.ProductUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public List<ProductShowDto> getAllProducts() {
        return productService.findAll();
    }

    // URL: http://localhost:8080/products/{id}
    @GetMapping("/{id}")
    public ProductShowDto getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    // URL: http://localhost:8080/products
    @PostMapping
    public ProductShowDto createProduct(@RequestBody ProductCreateDto dto) {
        return productService.create(dto);
    }

    // URL: http://localhost:8080/products/{id}
    @PutMapping("/{id}")
    public ProductShowDto updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDto dto) {
        return productService.update(id, dto);
    }

    // URL: http://localhost:8080/products/{id}
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}

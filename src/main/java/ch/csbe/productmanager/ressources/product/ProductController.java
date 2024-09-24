package ch.csbe.productmanager.ressources.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id) {
        return "Product with ID: " + id;
    }

    @PostMapping
    public String createProduct(@RequestBody String product) {
        return "Product added: " + product;
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody String product) {
        return "Product with ID: " + id + " updated: " + product;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return "Product with ID: " + id + " deleted";
    }
}

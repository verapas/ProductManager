package ch.csbe.productmanager.ressources.product;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id) {
        return "Produkt mit ID: " + id;
    }

    @PostMapping
    public String createProduct(@RequestBody String product) {
        return "Produkt hinzugefügt: " + product;
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody String product) {
        return "Produkt mit ID: " + id + " aktualisiert: " +product;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return "Produkt mit ID: " + id + " gelöscht";
    }

}

package ch.csbe.productmanager.ressources.product;

import ch.csbe.productmanager.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product findById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with the id " + id + " could not be found!"));
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Product create(Product productToSave) {
        return this.productRepository.save(productToSave);
    }

    public Product update(Long id, Product productToUpdate) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with the id " + id + " could not be found!"));
        product.setName(productToUpdate.getName());
        product.setPrice(productToUpdate.getPrice());
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}

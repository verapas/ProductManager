package ch.csbe.productmanager.resources.product;

import ch.csbe.productmanager.exception.ResourceNotFoundException;
import ch.csbe.productmanager.resources.product.dto.ProductCreateDto;
import ch.csbe.productmanager.resources.product.dto.ProductShowDto;
import ch.csbe.productmanager.resources.product.dto.ProductUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Geschäftslogik für die Verwaltung von Produkten.
 * Umfasst Methoden zum Erstellen, Abrufen, Aktualisieren und Löschen von Produkten.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    /**
     * Findet ein Produkt anhand der ID und gibt es als ProductShowDto zurück.
     */
    public ProductShowDto findById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produkt mit der ID " + id + " konnte nicht gefunden werden!"));
        return productMapper.toShowDto(product);
    }


    /**
     * Findet alle Produkte und gibt sie als Liste von ProductShowDto zurück.
     * Optional kann nach einer Kategorie gefiltert werden.
     */
    public List<ProductShowDto> findAll(String filterByCategory) {
        List<Product> products = this.productRepository.findAll();
        List<ProductShowDto> productShowDtos = new ArrayList<>();
        for (Product product : products) {
            ProductShowDto mappedProduct = productMapper.toShowDto(product);
            productShowDtos.add(mappedProduct);
        }
        return productShowDtos;
    }

    /**
     * Erstellt ein neues Produkt basierend auf den Daten im ProductCreateDto.
     */
    public ProductShowDto create(ProductCreateDto dto) {
        Product product = productMapper.toEntity(dto);
        Product savedProduct = this.productRepository.save(product);
        return productMapper.toShowDto(savedProduct);
    }

    /**
     * Aktualisiert ein bestehendes Produkt anhand der ID und der neuen Daten.
     */
    public ProductShowDto update(Long id, ProductUpdateDto dto) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produkt mit der ID " + id + " konnte nicht gefunden werden!"));
        productMapper.updateEntity(dto, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toShowDto(updatedProduct);
    }

    /**
     * Löscht ein Produkt anhand der ID.
     */
    public void delete(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produkt mit der ID " + id + " konnte nicht gefunden werden!"));
        productRepository.delete(product);
    }
}

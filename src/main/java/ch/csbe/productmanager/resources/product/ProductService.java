package ch.csbe.productmanager.resources.product;

import ch.csbe.productmanager.exception.ResourceNotFoundException;
import ch.csbe.productmanager.resources.product.dto.ProductCreateDto;
import ch.csbe.productmanager.resources.product.dto.ProductShowDto;
import ch.csbe.productmanager.resources.product.dto.ProductUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductShowDto findById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with the id " + id + " could not be found!"));
        return productMapper.toShowDto(product);
    }

    public List<ProductShowDto> findAll(String filterByCategory) {
        List<Product> products = this.productRepository.findAll();
        List<ProductShowDto> productShowDtos = new ArrayList<>();
        for (Product product : products) {
            ProductShowDto mappedProduct = productMapper.toShowDto(product);
            productShowDtos.add(mappedProduct);
        }
        return productShowDtos;
    }

    public ProductShowDto create(ProductCreateDto dto) {
        Product product = productMapper.toEntity(dto);
        Product savedProduct = this.productRepository.save(product);
        return productMapper.toShowDto(savedProduct);
    }

    public ProductShowDto update(Long id, ProductUpdateDto dto) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with the id " + id + " could not be found!"));
        productMapper.updateEntity(dto, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toShowDto(updatedProduct);
    }

    public void delete(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with the id " + id + " could not be found!"));
        productRepository.delete(product);
    }
}

package ch.csbe.productmanager.resources.category;

import ch.csbe.productmanager.exception.ResourceNotFoundException;
import ch.csbe.productmanager.resources.category.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Geschäftslogik für die Verwaltung von Kategorien.
 * Umfasst Methoden zum Erstellen, Abrufen, Aktualisieren und Löschen von Kategorien.
 */
@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * Findet alle Kategorien und gibt sie als Liste von CategoryShowDto zurück.
     */
    public List<CategoryShowDto> findAll() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toShowDto)
                .collect(Collectors.toList());
    }

    /**
     * Findet eine Kategorie anhand der ID.
     */
    public CategoryDetailDto findById(Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategorie mit der ID " + id + " konnte nicht gefunden werden!"));
        return categoryMapper.toDetailDto(category);
    }

    /**
     * Erstellt eine neue Kategorie auf Basis der gegebenen CategoryCreateDto.
     */
    public CategoryDetailDto create(CategoryCreateDto dto) {
        Category category = categoryMapper.toEntity(dto);
        Category savedCategory = this.categoryRepository.save(category);
        return categoryMapper.toDetailDto(savedCategory);
    }

    /**
     * Aktualisiert eine bestehende Kategorie anhand der ID und der neuen Daten.
     */
    public CategoryDetailDto update(Long id, CategoryUpdateDto dto) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategorie mit der ID " + id + " konnte nicht gefunden werden!"));
        categoryMapper.updateEntity(dto, category);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toDetailDto(updatedCategory);
    }

    /**
     * Löscht eine Kategorie anhand der ID.
     */
    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }
}

package ch.csbe.productmanager.resources.category;

import ch.csbe.productmanager.exception.ResourceNotFoundException;
import ch.csbe.productmanager.resources.category.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryShowDto> findAll() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toShowDto)
                .collect(Collectors.toList());
    }

    public CategoryDetailDto findById(Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategorie mit der ID " + id + " konnte nicht gefunden werden!"));
        return categoryMapper.toDetailDto(category);
    }

    public CategoryDetailDto create(CategoryCreateDto dto) {
        Category category = categoryMapper.toEntity(dto);
        Category savedCategory = this.categoryRepository.save(category);
        return categoryMapper.toDetailDto(savedCategory);
    }

    public CategoryDetailDto update(Long id, CategoryUpdateDto dto) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategorie mit der ID " + id + " konnte nicht gefunden werden!"));
        categoryMapper.updateEntity(dto, category);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toDetailDto(updatedCategory);
    }

    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }
}

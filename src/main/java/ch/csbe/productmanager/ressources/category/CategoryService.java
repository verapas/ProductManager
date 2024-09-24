package ch.csbe.productmanager.ressources.category;

import ch.csbe.productmanager.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with the id " + id + " could not be found!"));
    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public Category create(Category categoryToSave) {
        return this.categoryRepository.save(categoryToSave);
    }

    public Category update(Long id, Category categoryToUpdate) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with the id " + id + " could not be found!"));
        category.setName(categoryToUpdate.getName());
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}

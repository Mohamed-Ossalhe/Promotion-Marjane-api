package ma.youcode.services;

import ma.youcode.repositories.CategoryProductRepository;
import ma.youcode.entities.CategoryProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CategoryProductService {
    private final CategoryProductRepository repository;


    public CategoryProduct saveCategoryProduct(CategoryProduct categoryProduct) {
        return repository.save(categoryProduct);
    }


    public List<CategoryProduct> getAllCategoryProducts() {
        return repository.findAll();
    }


    public Optional<CategoryProduct> getCategoryProductById(UUID id) {
        return repository.findById(id);
    }


    public CategoryProduct updateCategoryProduct(CategoryProduct categoryProduct) {
        return repository.save(categoryProduct);
    }


    public void deleteCategoryProduct(UUID id) {
        repository.deleteById(id);
    }
}

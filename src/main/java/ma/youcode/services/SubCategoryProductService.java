package ma.youcode.services;

import ma.youcode.repositories.SubCategoryProductRepository;
import ma.youcode.entities.SubCategoryProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class SubCategoryProductService {
    private final SubCategoryProductRepository repository;

    public SubCategoryProduct saveSubCategoryProduct(SubCategoryProduct subCategoryProduct) {
        return repository.save(subCategoryProduct);
    }


    public List<SubCategoryProduct> getAllSubCategoryProducts() {
        return repository.findAll();
    }


    public Optional<SubCategoryProduct> getSubCategoryProductById(UUID id) {
        return repository.findById(id);
    }


    public SubCategoryProduct updateSubCategoryProduct(SubCategoryProduct subCategoryProduct) {
        return repository.save(subCategoryProduct);
    }

    public void deleteSubCategoryProduct(UUID id) {
        repository.deleteById(id);
    }
}
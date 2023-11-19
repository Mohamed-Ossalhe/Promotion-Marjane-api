package ma.youcode.controllers;

import ma.youcode.entities.CategoryProduct;
import ma.youcode.services.CategoryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/category-product")
public class CategoryProductController {
    private final CategoryProductService service;


    @PostMapping("/")
    public ResponseEntity<CategoryProduct> createCategoryProduct(@RequestBody CategoryProduct categoryProduct) {
        CategoryProduct createdCategoryProduct = service.saveCategoryProduct(categoryProduct);
        return new ResponseEntity<>(createdCategoryProduct, HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<List<CategoryProduct>> getAllCategoryProducts() {
        List<CategoryProduct> categoryProducts = service.getAllCategoryProducts();
        return new ResponseEntity<>(categoryProducts, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryProduct> getCategoryProductById(@PathVariable("id") UUID id) {
        return service
                .getCategoryProductById(id)
                .map(categoryProduct -> new ResponseEntity<>(categoryProduct, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryProduct> updateCategoryProduct(@PathVariable UUID id, @RequestBody CategoryProduct categoryProduct) {
        if (service.getCategoryProductById(categoryProduct.getId()).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        categoryProduct.setId(id);
        CategoryProduct updatedCategoryProduct = service.updateCategoryProduct(categoryProduct);
        return new ResponseEntity<>(updatedCategoryProduct, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryProduct(@PathVariable("id") UUID id) {
        if (service.getCategoryProductById(id).isPresent()) {
            service.deleteCategoryProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
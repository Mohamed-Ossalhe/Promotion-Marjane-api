package ma.youcode.controllers;

import ma.youcode.entities.SubCategoryProduct;
import ma.youcode.services.SubCategoryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/subcategory-product")
public class SubCategoryProductController {
    private final SubCategoryProductService subCategoryProductService;

    @PostMapping
    public ResponseEntity<SubCategoryProduct> createSubCategoryProduct(@RequestBody SubCategoryProduct subCategoryProduct) {
        SubCategoryProduct savedSubCategoryProduct = subCategoryProductService.saveSubCategoryProduct(subCategoryProduct);
        return new ResponseEntity<>(savedSubCategoryProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubCategoryProduct>> getAllSubCategoryProducts() {
        List<SubCategoryProduct> subCategoryProducts = subCategoryProductService.getAllSubCategoryProducts();
        return new ResponseEntity<>(subCategoryProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategoryProduct> getSubCategoryProductById(@PathVariable UUID id) {
        return ResponseEntity.of(Optional.empty());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategoryProduct> updateSubCategoryProduct(@PathVariable UUID id, @RequestBody SubCategoryProduct subCategoryProduct) {
        if (subCategoryProductService.getSubCategoryProductById(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        subCategoryProduct.setId(id);
        SubCategoryProduct updatedSubCategoryProduct = subCategoryProductService.updateSubCategoryProduct(subCategoryProduct);
        return new ResponseEntity<>(updatedSubCategoryProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategoryProduct(@PathVariable UUID id) {
        if (subCategoryProductService.getSubCategoryProductById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        subCategoryProductService.deleteSubCategoryProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
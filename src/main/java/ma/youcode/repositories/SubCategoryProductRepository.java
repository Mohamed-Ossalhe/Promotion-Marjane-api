package ma.youcode.repositories;

import ma.youcode.entities.SubCategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubCategoryProductRepository extends JpaRepository<SubCategoryProduct, UUID> {
}
package ma.youcode.repositories;

import ma.youcode.entities.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryProductRepository extends JpaRepository<CategoryProduct, UUID> {
}

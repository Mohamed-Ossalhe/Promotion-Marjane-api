package ma.youcode.repositories;

import ma.youcode.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PromotionRepository extends JpaRepository<Promotion, UUID> {
    public Optional<Promotion> findPromotionByPercentage(Double percentage);
}

package ma.youcode.repositories;

import ma.youcode.entities.Centre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CentreRepository extends JpaRepository<Centre, UUID> {
}

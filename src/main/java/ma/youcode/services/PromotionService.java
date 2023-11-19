package ma.youcode.services;

import ma.youcode.repositories.PromotionRepository;
import ma.youcode.entities.Promotion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PromotionService {
    private final PromotionRepository repository;


    public Promotion savePromotion(Promotion promotion) {
        return repository.save(promotion);
    }

    public List<Promotion> getAllPromotions() {
        LocalTime currentTime = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(12,0);
        if (currentTime.isAfter(startTime) || currentTime.isBefore(endTime))
            return repository.findAll();
        return new ArrayList<Promotion>();
    }

    public Optional<Promotion> getPromotionById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Promotion> getPromotionByPercentage(Double percentage) { return repository.findPromotionByPercentage(percentage); }

    public Promotion updatePromotion(Promotion promotion) {
        return repository.save(promotion);
    }


    public void deletePromotion(UUID id) {
        repository.deleteById(id);
    }
}
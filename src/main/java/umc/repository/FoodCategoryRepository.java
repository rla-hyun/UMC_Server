package umc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}

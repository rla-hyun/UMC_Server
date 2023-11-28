package umc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

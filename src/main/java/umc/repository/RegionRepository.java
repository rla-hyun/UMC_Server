package umc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}

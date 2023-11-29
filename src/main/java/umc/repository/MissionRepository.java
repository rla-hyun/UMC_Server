package umc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}

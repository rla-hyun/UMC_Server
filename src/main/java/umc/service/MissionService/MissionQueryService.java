package umc.service.MissionService;

import umc.domain.Mission;

import java.util.Optional;

public interface MissionQueryService {
    Optional<Mission> findMission(Long id);
}

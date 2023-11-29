package umc.service.MissionService;

import umc.domain.Mission;
import umc.web.dto.MissionRequestDTO;

public interface MissionCommandService {

    Mission createMission(Long storeId, MissionRequestDTO.MissionDTO request);
}

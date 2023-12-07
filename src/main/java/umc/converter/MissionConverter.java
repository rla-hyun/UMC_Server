package umc.converter;

import umc.domain.Mission;
import umc.web.dto.MissionRequestDTO;
import umc.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.MissionDTO request) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();
    }

    public static MissionResponseDTO.missionResultDTO toCreateMission(Mission mission) {
        return MissionResponseDTO.missionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.missionCompleteDTO toCompleteMission(Mission mission) {
        return MissionResponseDTO.missionCompleteDTO.builder()
                .storeName(mission.getStore().getName())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }
}

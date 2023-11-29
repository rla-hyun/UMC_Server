package umc.converter;


import umc.domain.enums.MissionStatus;
import umc.domain.mapping.MemberMission;
import umc.web.dto.MemberMissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {


    public static MemberMissionResponseDTO.MemberMissionResultDTO toChallengeMission(MemberMission memberMission) {
        return MemberMissionResponseDTO.MemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission() {
        return MemberMission.builder()
                .build();
    }
}

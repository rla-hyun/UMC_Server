package umc.converter;


import org.springframework.data.domain.Page;
import umc.domain.Mission;
import umc.domain.enums.MissionStatus;
import umc.domain.mapping.MemberMission;
import umc.web.dto.MemberMissionResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MemberMissionResponseDTO.MissionPreviewDTO missionPreviewDTO(Mission mission) {
        return MemberMissionResponseDTO.MissionPreviewDTO.builder()
                .storeName(mission.getStore().getName())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }

    public static MemberMissionResponseDTO.MissionPreviewListDTO missionPreviewListDTO(Page<Mission> missionList) {
        List<MemberMissionResponseDTO.MissionPreviewDTO> missionPreviewDTOList = missionList.stream()
                .map(MemberMissionConverter::missionPreviewDTO).collect(Collectors.toList());

        return MemberMissionResponseDTO.MissionPreviewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .missionList(missionPreviewDTOList)
                .build();
    }
}

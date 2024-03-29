package umc.service.MemberService;

import umc.domain.Member;
import umc.domain.Mission;
import umc.domain.mapping.MemberMission;
import umc.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);

    MemberMission AddMission(Long memberId, Long missionId);

    Mission ChangeMission(Long missionId, Long memberId);

}

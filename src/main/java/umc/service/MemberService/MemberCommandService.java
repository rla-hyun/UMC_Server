package umc.service.MemberService;

import umc.domain.Member;
import umc.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);

    boolean existsById(Long id);
}

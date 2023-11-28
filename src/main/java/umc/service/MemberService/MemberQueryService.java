package umc.service.MemberService;

import umc.domain.Member;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);

    boolean existsById(Long id);
}

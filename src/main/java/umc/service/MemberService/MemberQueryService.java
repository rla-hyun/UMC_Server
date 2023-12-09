package umc.service.MemberService;

import org.springframework.data.domain.Page;
import umc.domain.Member;
import umc.domain.Review;
import umc.domain.Mission;
import umc.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);

    boolean existsById(Long id);

    Page<Review> getReviewList(Long memberId, Integer page);

    Page<Mission> getMissionList(Long memberId, Integer page);
}

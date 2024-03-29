package umc.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.Member;
import umc.domain.Review;
import umc.domain.enums.MissionStatus;
import umc.repository.FoodCategoryRepository;
import umc.repository.MemberRepository;
import umc.repository.ReviewRepository;
import umc.domain.Mission;
import umc.domain.mapping.MemberMission;
import umc.repository.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return foodCategoryRepository.existsById(id);
    }

    @Override
    public Page<Review> getReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        Page<Review> MemberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return MemberPage;
    }

    public Page<Mission> getMissionList(Long memberId, Integer page, Integer status) {
        List<MemberMission> memberMissionList = memberMissionRepository.findAllByMemberId(memberId);

        List<Mission> filterMissionList = memberMissionList.stream()
                .filter(mm -> filterMission(mm.getStatus(), status))
                .map(MemberMission::getMission)
                .collect(Collectors.toList());

        return new PageImpl<>(filterMissionList, PageRequest.of(page, 10), filterMissionList.size());
    }

    private boolean filterMission(MissionStatus missionStatus, int status) {
        return (status == 1 && missionStatus == MissionStatus.CHALLENGING) ||
                (status == 2 && missionStatus == MissionStatus.COMPLETE);
    }
}

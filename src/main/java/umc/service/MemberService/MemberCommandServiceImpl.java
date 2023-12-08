package umc.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.apiPayload.code.status.ErrorStatus;
import umc.apiPayload.exception.handler.FoodCategoryHandler;
import umc.apiPayload.exception.handler.MemberMissionHandler;
import umc.converter.MemberConverter;
import umc.converter.MemberMissionConverter;
import umc.converter.MemberPreferConverter;
import umc.domain.FoodCategory;
import umc.domain.Member;
import umc.domain.Mission;
import umc.domain.mapping.MemberMission;
import umc.domain.mapping.MemberPrefer;
import umc.repository.FoodCategoryRepository;
import umc.repository.MemberMissionRepository;
import umc.repository.MemberRepository;
import umc.repository.MissionRepository;
import umc.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public MemberMission AddMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId).get();
        Mission mission = missionRepository.findById(missionId).get();
        MemberMission existMission = memberMissionRepository.findByMemberAndMission(member, mission);
        if(existMission != null) {
            throw new MemberMissionHandler(ErrorStatus.MISSION_CONFLICT);
        }
        MemberMission memberMission = MemberMissionConverter.toMemberMission();

        memberMission.setMember(member);
        memberMission.setMission(mission);

        return memberMissionRepository.save(memberMission);
    }

    @Override
    @Transactional
    public Mission ChangeMission(Long missionId, Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        Mission mission = missionRepository.findById(missionId).get();
        MemberMission memberMission = memberMissionRepository.findByMemberAndMission(member, mission);
        memberMission.setStatus();
        memberMissionRepository.save(memberMission);

        return mission;
    }
}

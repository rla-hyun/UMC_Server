package umc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.Member;
import umc.domain.Mission;
import umc.domain.mapping.MemberMission;

import java.util.List;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    MemberMission findByMemberAndMission(Member member, Mission mission);

    List<MemberMission> findAllByMemberId(Long memberId);

    boolean existsByMemberAndMission(Member member, Mission mission);
}

package umc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.Member;
import umc.domain.Mission;
import umc.domain.mapping.MemberMission;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    MemberMission findByMemberAndMission(Member member, Mission mission);
}

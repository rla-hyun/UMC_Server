package umc.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.Mission;
import umc.repository.MissionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService{

    private final MissionRepository missionRepository;
    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }
}

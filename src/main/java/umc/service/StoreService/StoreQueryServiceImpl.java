package umc.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.Region;
import umc.repository.RegionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final RegionRepository regionRepository;

    @Override
    public Optional<Region> findRegion(Long id) {
        return regionRepository.findById(id);
    }
}

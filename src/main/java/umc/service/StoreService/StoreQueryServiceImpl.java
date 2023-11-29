package umc.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.Region;
import umc.domain.Store;
import umc.repository.RegionRepository;
import umc.repository.StoreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;


    @Override
    public Optional<Region> findRegion(Long id) {
        return regionRepository.findById(id);
    }

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }
}

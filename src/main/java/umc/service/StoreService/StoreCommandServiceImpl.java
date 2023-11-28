package umc.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.converter.StoreConverter;
import umc.domain.Review;
import umc.domain.Store;
import umc.repository.RegionRepository;
import umc.repository.StoreRepository;
import umc.web.dto.StoreRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public Store AddStore(Long regionId, StoreRequestDTO.StoreDTO request) {
        System.out.println("AddStore");
        Store store = StoreConverter.toStore(request);

        store.setRegion(regionRepository.findById(regionId).get());

        return storeRepository.save(store);
    }
}

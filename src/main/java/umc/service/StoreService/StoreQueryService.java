package umc.service.StoreService;

import umc.domain.Region;
import umc.domain.Store;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Region> findRegion(Long id);

    Optional<Store> findStore(Long id);
}

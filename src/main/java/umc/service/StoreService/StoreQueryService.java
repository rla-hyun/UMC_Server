package umc.service.StoreService;

import umc.domain.Region;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Region> findRegion(Long id);
}

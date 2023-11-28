package umc.service.ReviewService;

import umc.domain.Store;

import java.util.Optional;

public interface ReviewQueryService {

    Optional<Store> findStore(Long id);
}

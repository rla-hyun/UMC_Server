package umc.service.StoreService;

import umc.domain.Review;
import umc.domain.Store;
import umc.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store AddStore(Long regionId, StoreRequestDTO.StoreDTO request);
}

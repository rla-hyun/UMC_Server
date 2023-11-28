package umc.service.ReviewService;

import umc.domain.Review;
import umc.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request);
}

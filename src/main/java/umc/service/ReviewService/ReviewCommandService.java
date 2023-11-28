package umc.service.ReviewService;

import umc.domain.Review;
import umc.web.dto.StoreRequestDTO;

public interface ReviewCommandService {

    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request);
}

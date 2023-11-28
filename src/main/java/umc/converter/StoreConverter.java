package umc.converter;

import umc.domain.Review;
import umc.domain.Store;
import umc.web.dto.StoreRequestDTO;
import umc.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {


    public static StoreResponseDTO.StoreResultDTO toAddStore(Store store) {
        return StoreResponseDTO.StoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.StoreDTO request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }

    public static StoreResponseDTO.ReviewResultDTO toCreateReviewDTO(Review review) {
        return StoreResponseDTO.ReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(StoreRequestDTO.ReviewDTO request) {
        return Review.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .score(request.getScore())
                .build();
    }
}

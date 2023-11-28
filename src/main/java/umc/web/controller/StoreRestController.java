package umc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.apiPayload.ApiResponse;
import umc.converter.StoreConverter;
import umc.domain.Review;
import umc.service.ReviewService.StoreCommandService;
import umc.validation.annotation.ExistMember;
import umc.validation.annotation.ExistStore;
import umc.web.dto.StoreRequestDTO;
import umc.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {

    private final StoreCommandService reviewCommandService;

    @PostMapping("/review/{storeId}")
    public ApiResponse<StoreResponseDTO.ReviewResultDTO> review(@RequestBody @Valid StoreRequestDTO.ReviewDTO request,
                                                                @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = reviewCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewDTO(review));
    }
}


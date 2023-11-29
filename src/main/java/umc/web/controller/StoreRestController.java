package umc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.apiPayload.ApiResponse;
import umc.converter.MissionConverter;
import umc.converter.StoreConverter;
import umc.domain.Mission;
import umc.domain.Review;
import umc.domain.Store;
import umc.service.MissionService.MissionCommandService;
import umc.service.ReviewService.ReviewCommandService;
import umc.service.StoreService.StoreCommandService;
import umc.validation.annotation.ExistMember;
import umc.validation.annotation.ExistRegion;
import umc.validation.annotation.ExistStore;
import umc.web.dto.MissionRequestDTO;
import umc.web.dto.MissionResponseDTO;
import umc.web.dto.StoreRequestDTO;
import umc.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreRestController {

    private final ReviewCommandService reviewCommandService;
    private final StoreCommandService storeCommandService;
    private final MissionCommandService missionCommandService;

    @PostMapping("/review/{storeId}")
    public ApiResponse<StoreResponseDTO.ReviewResultDTO> review(@RequestBody @Valid StoreRequestDTO.ReviewDTO request,
                                                                @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = reviewCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewDTO(review));
    }

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponseDTO.StoreResultDTO> store(@RequestBody @Valid StoreRequestDTO.StoreDTO request,
                                                              @ExistRegion @PathVariable(name = "regionId") Long regionId) {
        Store store = storeCommandService.AddStore(regionId, request);
        return ApiResponse.onSuccess(StoreConverter.toAddStore(store));
    }

    @PostMapping("/{storeId}/mission")
    public ApiResponse<MissionResponseDTO.missionResultDTO> mission(@RequestBody @Valid MissionRequestDTO.MissionDTO request,
                                                                    @ExistStore @PathVariable(name = "storeId") Long storeId) {
        Mission mission = missionCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toCreateMission(mission));
    }
}


package umc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import umc.service.StoreService.StoreQueryService;
import umc.validation.annotation.ExistMember;
import umc.validation.annotation.CheckPage;
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
    private final StoreQueryService storeQueryService;

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

    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{storeId}/reviews")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @CheckPage @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = storeQueryService.getReviewList(storeId,page-1);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{storeId}/missions")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponseDTO.MissionPreviewListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                              @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Mission> missionList = storeQueryService.getMissionList(storeId, page-1);
        return ApiResponse.onSuccess(StoreConverter.missionPreviewListDTO(missionList));
    }
}


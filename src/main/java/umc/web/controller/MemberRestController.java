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
import umc.converter.MemberConverter;
import umc.converter.MemberMissionConverter;
import umc.domain.Member;
import umc.domain.Review;
import umc.domain.mapping.MemberMission;
import umc.service.MemberService.MemberCommandService;
import umc.service.MemberService.MemberQueryService;
import umc.validation.annotation.CheckPage;
import umc.validation.annotation.ExistMember;
import umc.validation.annotation.ExistMission;
import umc.web.dto.MemberMissionResponseDTO;
import umc.web.dto.MemberRequestDTO;
import umc.web.dto.MemberResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/{missionId}")
    public ApiResponse<MemberMissionResponseDTO.MemberMissionResultDTO> memberMission(@ExistMission @PathVariable(name = "missionId") Long missionId,
                                             @ExistMember @RequestParam(name = "memberId") Long memberId) {
        MemberMission memberMIssion = memberCommandService.AddMission(memberId, missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toChallengeMission(memberMIssion));
    }

    @Operation(summary = "작성한 리뷰 목록 조회 API",description = "사용자가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/reviews")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자 아이디, query String 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MemberResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistMember @RequestParam(name = "memberId") Long memberId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = memberQueryService.getReviewList(memberId, page-1);
        return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDTO(reviewList));

    }
}

package umc.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.apiPayload.ApiResponse;
import umc.converter.MemberConverter;
import umc.converter.MemberMissionConverter;
import umc.converter.MissionConverter;
import umc.domain.Member;
import umc.domain.Mission;
import umc.domain.mapping.MemberMission;
import umc.service.MemberService.MemberCommandService;
import umc.validation.annotation.ExistMember;
import umc.validation.annotation.ExistMission;
import umc.web.dto.MemberMissionResponseDTO;
import umc.web.dto.MemberRequestDTO;
import umc.web.dto.MemberResponseDTO;
import umc.web.dto.MissionResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

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

    @GetMapping("/{missionId}/complete")
    public ApiResponse<MissionResponseDTO.missionCompleteDTO> missionComplete(@ExistMission @PathVariable(name = "missionId") Long missionId,
                                                                              @ExistMember @RequestParam(name = "memberId") Long memberId) {
        Mission mission = memberCommandService.ChangeMission(missionId, memberId);
        return ApiResponse.onSuccess(MissionConverter.toCompleteMission(mission));
    }
}

package com.wanted.application.controller;

import com.wanted.application.dto.ApplicationRequest;
import com.wanted.application.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = "Application(채용공고 지원) API")
@RequestMapping(produces = "application/json; charset=utf8")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Operation(summary = "채용공고 지원", description = "채용공고 지원 요청 API")
    @ApiResponses({
            @ApiResponse(code = 201, message = "요청 성공"),
            @ApiResponse(code = 404, message = "실패 - 회사/채용공고 정보 찾을 수 없음"),
            @ApiResponse(code = 400, message = "실패 - request 입력 값이 요청 형식에 맞지 않음"),
            @ApiResponse(code = 409, message = "실패 - 동일한 채용공고에 중복 지원 불가능")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/wanted/applications")
    public ResponseEntity submitApplication(@Valid @RequestBody ApplicationRequest request) {
        Long userId = request.getUserId();
        Long postId = request.getPostId();
        applicationService.submitApplication(userId, postId);

        return new ResponseEntity<>("해당 채용공고에 지원이 완료되었습니다.", HttpStatus.CREATED);
    }

}

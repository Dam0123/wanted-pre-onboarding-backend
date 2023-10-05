package com.wanted.application.controller;

import com.wanted.application.dto.ApplicationRequest;
import com.wanted.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/wanted/applications")
    public ResponseEntity submitApplication(@Valid @RequestBody ApplicationRequest request) {
        Long userId = request.getUserId();
        Long postId = request.getPostId();
        applicationService.submitApplication(userId, postId);

        return new ResponseEntity<>("해당 채용공고에 지원이 완료되었습니다.", HttpStatus.CREATED);
    }

}

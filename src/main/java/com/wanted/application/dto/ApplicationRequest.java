package com.wanted.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplicationRequest {

    @Positive(message = "userId는 1 이상의 양수만 가능합니다.")
    private Long userId;
    @Positive(message = "postId는 1 이상의 양수만 가능합니다.")
    private Long postId;
}

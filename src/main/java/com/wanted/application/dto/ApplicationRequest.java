package com.wanted.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplicationRequest {
    private Long userId;
    private Long postId;
}

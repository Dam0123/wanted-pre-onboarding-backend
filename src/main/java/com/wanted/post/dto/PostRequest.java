package com.wanted.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRequest {

    @NotBlank(message = "position은 필수 입력 항목입니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣\\d\\s]*$", message = "position은 특수문자를 제외하고 입력 가능합니다.")
    private String position;

    @NotBlank(message = "description은 필수 입력 항목입니다.")
    private String description;

    @NotNull(message = "reward는 필수 입력 항목입니다.")
    @Min(value = 0, message = "reward는 0 이상이어야 합니다.")
    private Integer reward;

    @NotBlank(message = "skill은 필수 입력 항목입니다.")
    private String skill;

    @NotBlank(message = "deadline은 필수 입력 항목입니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣\\d\\s-]*$", message = "deadline은 영어, 한글, 숫자, 공백, 하이픈(-)만 입력 가능합니다.")
    private String deadline;

    @NotNull(message = "companyId는 필수 입력 항목입니다.")
    private Long companyId;
}

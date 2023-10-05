package com.wanted.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRequest {

    private String position;
    private String description;
    private Integer reward;
    private String skill;
    private String deadline;
    private Long companyId;
}

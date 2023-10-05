package com.wanted.post.dto;

import com.wanted.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostDetailResponse {
    private Long postId;
    private String position;
    private String description;
    private Integer reward;
    private String skill;
    private String deadline;
    private String companyName;
    private String country;
    private String region;
    private List<Long> postIdList;

    public static PostDetailResponse of(Post post,  List<Long> postIdList) {
        return new PostDetailResponse(post.getId(), post.getPosition(), post.getDescription(),
                post.getReward(), post.getSkill(), post.getDeadline(), post.getCompany().getCompanyName(),
                post.getCompany().getCountry(), post.getCompany().getRegion(), postIdList);
    }

}

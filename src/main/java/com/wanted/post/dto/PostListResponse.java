package com.wanted.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostListResponse {

    private Long postId;
    private String position;
    private Integer reward;
    private String skill;
    private String companyName;
    private String country;
    private String region;

    public static PostListResponse of(PostListResponse post) {
        return new PostListResponse(post.postId, post.getPosition(),
                post.getReward(), post.getSkill(), post.companyName,
                post.getCountry(), post.getRegion());
    }

    @QueryProjection
    public PostListResponse(Long postId, String position, Integer reward, String skill,
                            String companyName, String country, String region) {
        this.postId = postId;
        this.position = position;
        this.reward = reward;
        this.skill = skill;
        this.companyName = companyName;
        this.country = country;
        this.region = region;
    }
}

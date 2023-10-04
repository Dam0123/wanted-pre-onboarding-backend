package com.wanted.post.dto;

import com.wanted.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostIdResponse {
    private Long postId;

    public static PostIdResponse of(Post post) {
        return new PostIdResponse(post.getId());
    }
}

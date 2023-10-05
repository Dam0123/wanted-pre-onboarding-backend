package com.wanted.post.repository;

import com.wanted.post.dto.PostListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostRepositoryCustom {
    Slice<PostListResponse> findByKeyword(String keyword, Pageable pageable);
}

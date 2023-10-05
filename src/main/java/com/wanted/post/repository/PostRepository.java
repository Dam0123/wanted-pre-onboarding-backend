package com.wanted.post.repository;

import com.wanted.company.domain.Company;
import com.wanted.post.domain.Post;
import com.wanted.post.dto.PostIdResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    List<PostIdResponse> findPostsByCompany(Company company);
}

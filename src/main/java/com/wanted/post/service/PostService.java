package com.wanted.post.service;

import com.wanted.company.service.CompanyService;
import com.wanted.exception.CustomException;
import com.wanted.exception.PostExceptionType;
import com.wanted.post.domain.Post;
import com.wanted.post.dto.PostRequest;
import com.wanted.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CompanyService companyService;

    //채용공고 등록 기능
    public void registerPost(PostRequest request) {
        Post post = Post.builder()
                .position(request.getPosition())
                .description(request.getDescription())
                .reward(request.getReward())
                .skill(request.getSkill())
                .deadline(request.getDeadline())
                .company(companyService.validateExistCompany(request.getCompanyId()))
                .build();

        postRepository.save(post);
    }

    //채용공고 수정 기능
    @Transactional
    public void updatePost(Long postId, PostRequest request) {
    Post findPost = validateExistPost(postId);

    if (!request.getCompanyId().equals(findPost.getCompany().getId())) {
        throw new CustomException(PostExceptionType.UNAUTHORIZED_ACCESS);
    }

    findPost.updatePostInfo(postId, request);
    }

    public Post validateExistPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostExceptionType.POST_DOES_NOT_EXIST));
    }

}

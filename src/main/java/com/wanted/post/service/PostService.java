package com.wanted.post.service;

import com.wanted.company.domain.Company;
import com.wanted.company.service.CompanyService;
import com.wanted.exception.CustomException;
import com.wanted.exception.PostExceptionType;
import com.wanted.post.domain.Post;
import com.wanted.post.dto.PostDetailResponse;
import com.wanted.post.dto.PostIdResponse;
import com.wanted.post.dto.PostRequest;
import com.wanted.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    //채용공고 삭제 기능
    @Transactional
    public void removeByPostIdAndCompanyId(Long postId, Long companyId) {
        Post post = validateExistPost(postId);

        if (!companyId.equals(post.getCompany().getId())) {
            throw new CustomException(PostExceptionType.UNAUTHORIZED_ACCESS);
        }

        postRepository.deleteById(postId);
    }

    public Post validateExistPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostExceptionType.POST_DOES_NOT_EXIST));
    }

    //채용공고 상세페이지 조회
    public PostDetailResponse findPost(Long postId) {
        Post post = validateExistPost(postId);
        List<Long> postIdList = findPostsByCompany(post.getCompany().getId())
                .stream()
                .map(PostIdResponse::getPostId)
                .collect(Collectors.toList());

        return PostDetailResponse.of(post,postIdList);
    }

    //특정회사의 채용공고 id 목록 조회
    public List<PostIdResponse> findPostsByCompany(Long companyId) {
        Company company = companyService.validateExistCompany(companyId);
        return postRepository.findPostsByCompany(company);
    }
}

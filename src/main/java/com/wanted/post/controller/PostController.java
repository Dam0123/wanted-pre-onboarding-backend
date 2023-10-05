package com.wanted.post.controller;

import com.wanted.post.dto.PostListResponse;
import com.wanted.post.dto.PostRequest;
import com.wanted.post.dto.PostSliceResponses;
import com.wanted.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/wanted/posts")
    public ResponseEntity registerPost(@Valid @RequestBody PostRequest request) {
        postService.registerPost(request);
        return new ResponseEntity<>("채용공고 등록이 완료되었습니다.", HttpStatus.CREATED);
    }

    @PatchMapping("/wanted/posts/{postId}")
    public ResponseEntity updatePost(@PathVariable Long postId,
            @Valid @RequestBody PostRequest request) {
        postService.updatePost(postId, request);
        return new ResponseEntity<>("채용공고 수정이 완료되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/wanted/posts/{postId}")
    public ResponseEntity removePost(@PathVariable Long postId,
                                     @RequestParam(value = "companyId") Long companyId) {
        postService.removeByPostIdAndCompanyId(postId, companyId);
        return new ResponseEntity<>("채용공고가 삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/wanted/posts/{postId}")
    public ResponseEntity findPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.findPost(postId));
    }

    @GetMapping("/wanted/posts")
    public ResponseEntity findPostsAll(@Positive @RequestParam("page") int page,
                                       @Positive @RequestParam("size") int size,
                                       @RequestParam("keyword") String keyword) {

        Slice<PostListResponse> posts = postService.findPostsAll(keyword, page, size);
        List<PostListResponse> responses = posts
                .stream()
                .map(PostListResponse::of)
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                new PostSliceResponses<>(responses, posts), HttpStatus.OK);
    }
}

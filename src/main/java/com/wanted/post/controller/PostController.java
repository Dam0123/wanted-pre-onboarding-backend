package com.wanted.post.controller;

import com.wanted.post.dto.PostRequest;
import com.wanted.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

}

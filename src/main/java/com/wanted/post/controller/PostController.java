package com.wanted.post.controller;

import com.wanted.post.dto.PostListResponse;
import com.wanted.post.dto.PostRequest;
import com.wanted.post.dto.PostSliceResponses;
import com.wanted.post.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
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
@Api(tags = "Post(채용공고) API")
@RequestMapping(value = "/wanted/posts", produces = "application/json; charset=utf8")
public class PostController {
    private final PostService postService;

    @Operation(summary = "채용공고 등록", description = "채용공고 등록 요청 API")
    @ApiResponses({
            @ApiResponse(code = 201, message = "요청 성공"),
            @ApiResponse(code = 404, message = "실패 - 회사 정보 찾을 수 없음"),
            @ApiResponse(code = 400, message = "실패 - request 입력 값이 요청 형식에 맞지 않음")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity registerPost(@Valid @RequestBody PostRequest request) {
        postService.registerPost(request);
        return new ResponseEntity<>("채용공고 등록이 완료되었습니다.", HttpStatus.CREATED);
    }

    @Operation(summary = "채용공고 수정", description = "채용공고 수정 요청 API")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청 성공"),
            @ApiResponse(code = 404, message = "실패 - 회사 정보 찾을 수 없음"),
            @ApiResponse(code = 403, message = "실패 - 본인 회사의 채용공고만 수정 가능"),
            @ApiResponse(code = 400, message = "실패 - request 입력 값이 요청 형식에 맞지 않음")
    })
    @PatchMapping("/{postId}")
    public ResponseEntity updatePost(@PathVariable Long postId,
            @Valid @RequestBody PostRequest request) {
        postService.updatePost(postId, request);
        return new ResponseEntity<>("채용공고 수정이 완료되었습니다.", HttpStatus.OK);
    }

    @Operation(summary = "채용공고 삭제", description = "채용공고 삭제 요청 API")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청 성공"),
            @ApiResponse(code = 404, message = "실패 - 회사/채용공고 정보 찾을 수 없음"),
            @ApiResponse(code = 403, message = "실패 - 본인 회사의 채용공고만 삭제 가능")
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity removePost(@PathVariable Long postId,
                                     @RequestParam(value = "companyId") Long companyId) {
        postService.removeByPostIdAndCompanyId(postId, companyId);
        return new ResponseEntity<>("채용공고가 삭제되었습니다.", HttpStatus.OK);
    }

    @Operation(summary = "채용공고 상세페이지 조회", description = "채용공고 상세페이지 조회 요청 API")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청 성공"),
            @ApiResponse(code = 404, message = "실패 - 채용공고 정보 찾을 수 없음")
    })
    @GetMapping("/{postId}")
    public ResponseEntity findPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.findPost(postId));
    }

    @Operation(summary = "채용공고 목록 조회 및 키워드 검색", description = "채용공고 목록 조회 및 키워드 검색 요청 API")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청 성공"),
            @ApiResponse(code = 404, message = "실패 - 채용공고 정보 찾을 수 없음"),
            @ApiResponse(code = 400, message = "실패 - 요청 파라미터가 올바르지 않음")
    })
    @GetMapping
    public ResponseEntity findPostsAll(@Positive @RequestParam("page") int page,
                                       @Positive @RequestParam("size") int size,
                                       @RequestParam(value = "keyword", required = false) String keyword) {

        Slice<PostListResponse> posts = postService.findPostsAll(keyword, page, size);
        List<PostListResponse> responses = posts
                .stream()
                .map(PostListResponse::of)
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                new PostSliceResponses<>(responses, posts), HttpStatus.OK);
    }
}

package com.wanted.post.controller;

import com.google.gson.Gson;
import com.wanted.company.domain.Company;
import com.wanted.exception.CustomException;
import com.wanted.exception.PostExceptionType;
import com.wanted.post.domain.Post;
import com.wanted.post.dto.PostDetailResponse;
import com.wanted.post.dto.PostListResponse;
import com.wanted.post.dto.PostRequest;
import com.wanted.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@MockBean(JpaMetamodelMappingContext.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("채용공고 등록 테스트")
    void registerPost() throws Exception {

        //채용공고 등록 성공 케이스
        PostRequest request = new PostRequest("백엔드 주니어 개발자", "공고 설명",
                1000000, "Java", "수시", 1L);
        String content = gson.toJson(request);

        doNothing().when(postService).registerPost(request);

        mockMvc.perform(
                        post("/wanted/posts")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andExpect(status().isCreated())
                .andDo(print());


        //채용공고 등록 실패 케이스 1 - deadline 값 미포함
        PostRequest badRequest1 = new PostRequest("백엔드 주니어 개발자", "공고 설명",
                1000000, "Java", "", 1L);

        String badContent1 = gson.toJson(badRequest1);

        mockMvc.perform(
                        post("/wanted/posts")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(badContent1)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());


        //채용공고 등록 실패 케이스 2 - reward 값에 음수 포함
        PostRequest badRequest2 = new PostRequest("백엔드 주니어 개발자", "공고 설명",
                -10, "Java", "2023-12-30", 1L);

        String badContent2 = gson.toJson(badRequest2);

        mockMvc.perform(
                        post("/wanted/posts")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(badContent2)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());


        //채용공고 등록 실패 케이스 3 - position 값에 특수문자 포함
        PostRequest badRequest3 = new PostRequest("백엔드 주니어 개발자!!!", "공고 설명",
                1000000, "Java", "2023-12-30", 1L);

        String badContent3 = gson.toJson(badRequest3);

        mockMvc.perform(
                        post("/wanted/posts")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(badContent3)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());


        //채용공고 등록 실패 케이스 4 - 유효하지 않은 companyId 전달
        PostRequest badRequest4 = new PostRequest("백엔드 주니어 개발자", "공고 설명",
                1000000, "Java", "2023-12-30", -1L);

        String badContent4 = gson.toJson(badRequest4);

        mockMvc.perform(
                        post("/wanted/posts")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(badContent4)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }


    @Test
    @DisplayName("채용공고 수정 테스트")
    void updatePost() throws Exception {

        //채용공고 수정 성공 케이스
        PostRequest request = new PostRequest("백엔드 주니어 개발자", "공고 설명",
                1000000, "Python", "수시", 1L);

        String content = gson.toJson(request);
        Long postId = 1L;

        doNothing().when(postService).updatePost(postId, request);

        mockMvc.perform(
                        patch("/wanted/posts/{postId}", postId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andExpect(status().isOk())
                .andDo(print());


        //채용공고 수정 실패 케이스 1 - deadline 값 미포함
        PostRequest badRequest1 = new PostRequest("백엔드 주니어 개발자", "공고 설명",
                1000000, "Java", "", 1L);

        String badContent1 = gson.toJson(badRequest1);

        mockMvc.perform(
                        patch("/wanted/posts/{postId}", postId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(badContent1)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());


        //채용공고 수정 실패 케이스 2 - reward 값에 음수 포함
        PostRequest badRequest2 = new PostRequest("백엔드 주니어 개발자", "공고 설명",
                -10, "Java", "2023-12-30", 1L);

        String badContent2 = gson.toJson(badRequest2);

        mockMvc.perform(
                        patch("/wanted/posts/{postId}", postId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(badContent2)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());


        //채용공고 수정 실패 케이스 3 - position 값에 특수문자 포함
        PostRequest badRequest3 = new PostRequest("백엔드 주니어 개발자!!!", "공고 설명",
                1000000, "Java", "2023-12-30", 1L);

        String badContent3 = gson.toJson(badRequest3);

        mockMvc.perform(
                        patch("/wanted/posts/{postId}", postId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(badContent3)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());


        //채용공고 수정 실패 케이스 4 - 유효하지 않은 companyId 전달
        PostRequest badRequest4 = new PostRequest("백엔드 주니어 개발자", "공고 설명",
                1000000, "Java", "2023-12-30", -1L);

        String badContent4 = gson.toJson(badRequest4);

        mockMvc.perform(
                        patch("/wanted/posts/{postId}", postId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(badContent4)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("채용공고 삭제 테스트")
    void removePost() throws Exception {

        Company company = Company.builder()
                .id(1L)
                .companyName("원티드")
                .industry("IT")
                .country("대한민국")
                .region("서울")
                .address("서울 강남구")
                .build();

        Post post  = Post.builder()
                .id(1L)
                .position("백엔드 주니어 개발자")
                .description("공고 설명")
                .reward(1000000)
                .skill("Java")
                .deadline("2023-12-30")
                .company(company)
                .build();


        //채용공고 삭제 성공 케이스
        doNothing().when(postService).removeByPostIdAndCompanyId(post.getId(), post.getCompany().getId());

        mockMvc.perform(
                        delete("/wanted/posts/{postId}", post.getId())
                                .param("companyId", "1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

        //채용공고 삭제 실패 케이스 2 - companyId 미포함
        mockMvc.perform(
                        delete("/wanted/posts/{postId}", post.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());


        //채용공고 삭제 실패 케이스 3- 올바르지 않은 companyId 형식
        mockMvc.perform(
                        delete("/wanted/posts/{postId}", 1L)
                                .param("companyId", "Id")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());


        //채용공고 삭제 실패 케이스 4 - 올바르지 않은 파라미터 이름 전달
        mockMvc.perform(
                        delete("/wanted/posts/{postId}", 1L)
                                .param("company", "1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("채용공고 상세페이지 조회 테스트")
    void findPost() throws Exception {
        Company company = Company.builder()
                .id(1L)
                .companyName("원티드")
                .industry("IT")
                .country("대한민국")
                .region("서울")
                .address("서울 강남구")
                .build();

        Post post  = Post.builder()
                .id(1L)
                .position("백엔드 주니어 개발자")
                .description("공고 설명")
                .reward(1000000)
                .skill("Java")
                .deadline("2023-12-30")
                .company(company)
                .build();

        List<Long> postIdList = Arrays.asList(1L, 2L, 3L);
        PostDetailResponse response = PostDetailResponse.of(post, postIdList);

        //채용공고 상세페이지 조회 성공 케이스
        when(postService.findPost(1L)).thenReturn(response);

        mockMvc.perform(
                        get("/wanted/posts/{postId}", 1L)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value(response.getPostId()))
                .andExpect(jsonPath("$.position").value(response.getPosition()))
                .andExpect(jsonPath("$.companyName").value(response.getCompanyName()))
                .andDo(print());


        //채용공고 상세페이지 조회 실패 케이스 - 채용공고 id가 유효하지 않을 때
        when(postService.findPost(2L)).thenThrow(new CustomException(PostExceptionType.POST_DOES_NOT_EXIST));

        mockMvc.perform(
                        get("/wanted/posts/{postId}", 2L)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("채용공고 목록 조회 및 검색 테스트")
    void findPostsAll() throws Exception {

        //채용공고 목록 조회 성공 케이스 1 - keyword 파라미터 안에 내용 없을 때
        Slice<PostListResponse> mockedSlice = getMockedSlice();

        when(postService.findPostsAll(anyString(), anyInt(), anyInt())).thenReturn(mockedSlice);

        mockMvc.perform(
                        get("/wanted/posts")
                                .param("page", "1")
                                .param("size", "3")
                                .param("keyword", "")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.posts").isArray())
                .andExpect(jsonPath("$.posts", hasSize(3)))
                .andExpect(jsonPath("$.posts[*].position").exists())
                .andExpect(jsonPath("$.pageable").exists())
                .andExpect(jsonPath("$.first").exists())
                .andExpect(jsonPath("$.last").exists())
                .andDo(print());


        //채용공고 목록 조회 성공 케이스 12 - keyword 파라미터 안에 내용 있을 때
        when(postService.findPostsAll(anyString(), anyInt(), anyInt())).thenReturn(mockedSlice);

        mockMvc.perform(
                        get("/wanted/posts")
                                .param("page", "1")
                                .param("size", "3")
                                .param("keyword", "Java")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.posts").isArray())
                .andExpect(jsonPath("$.posts", hasSize(3)))
                .andExpect(jsonPath("$.posts[*].position").exists())
                .andExpect(jsonPath("$.pageable").exists())
                .andExpect(jsonPath("$.first").exists())
                .andExpect(jsonPath("$.last").exists())
                .andDo(print());

        //채용공고 목록 조회 실패 케이스 1 - page 파라미터 미포함
        when(postService.findPostsAll(anyString(), anyInt(), anyInt())).thenReturn(mockedSlice);

        mockMvc.perform(
                        get("/wanted/posts")
                                .param("size", "3")
                                .param("keyword", "Java")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());

        //채용공고 목록 조회 실패 케이스 2 - keyword 파라미터 미포함
        when(postService.findPostsAll(anyString(), anyInt(), anyInt())).thenReturn(mockedSlice);

        mockMvc.perform(
                        get("/wanted/posts")
                                .param("page", "1")
                                .param("size", "3")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    private Slice<PostListResponse> getMockedSlice() {
        Company company = Company.builder()
                .id(1L)
                .companyName("원티드")
                .industry("IT")
                .country("대한민국")
                .region("서울")
                .address("서울 강남구")
                .build();

        Post post1  = Post.builder()
                .id(1L)
                .position("백엔드 주니어 개발자")
                .description("공고 설명")
                .reward(1000000)
                .skill("Java")
                .deadline("2023-12-30")
                .company(company)
                .build();

        Post post2  = Post.builder()
                .id(2L)
                .position("백엔드 주니어 개발자")
                .description("공고 설명")
                .reward(1000000)
                .skill("Java")
                .deadline("2023-12-30")
                .company(company)
                .build();

        Post post3  = Post.builder()
                .id(3L)
                .position("백엔드 주니어 개발자")
                .description("공고 설명")
                .reward(1000000)
                .skill("Python")
                .deadline("2023-12-30")
                .company(company)
                .build();

        List<PostListResponse> responses = new ArrayList<>();
        responses.add(PostListResponse.of(post1));
        responses.add(PostListResponse.of(post2));
        responses.add(PostListResponse.of(post3));

        Pageable pageable = PageRequest.of(0, 2);
        return new SliceImpl<>(responses, pageable, false);
    }

}
package com.wanted.application.controller;

import com.google.gson.Gson;
import com.wanted.application.dto.ApplicationRequest;
import com.wanted.application.service.ApplicationService;
import com.wanted.exception.ApplicationExceptionType;
import com.wanted.exception.CustomException;
import com.wanted.exception.PostExceptionType;
import com.wanted.exception.UserExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicationController.class)
@MockBean(JpaMetamodelMappingContext.class)
class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("채용공고 지원 테스트")
    void submitApplication() throws Exception {

        // 채용공고 지원 성공 케이스
        ApplicationRequest request = new ApplicationRequest(1L, 1L);
        String content = gson.toJson(request);

        doNothing().when(applicationService).submitApplication(request.getUserId(), request.getPostId());

        mockMvc.perform(
                        post("/wanted/applications")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andExpect(status().isCreated())
                .andDo(print());


        // 채용공고 지원 실패 케이스 1 - 중복 지원
        doThrow(new CustomException(ApplicationExceptionType.ALREADY_APPLIED))
                .when(applicationService)
                .submitApplication(1L, 1L);

        mockMvc.perform(
                        post("/wanted/applications")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andExpect(status().isConflict())
                .andDo(print());


        // 채용공고 지원 실패 케이스 2 - 해당 채용공고가 존재하지 않을 경우
        doThrow(new CustomException(PostExceptionType.POST_DOES_NOT_EXIST))
                .when(applicationService)
                .submitApplication(1L, 1L);

        mockMvc.perform(
                        post("/wanted/applications")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andExpect(status().isNotFound())
                .andDo(print());


        // 채용공고 지원 실패 케이스 3 - 사용자 정보가 올바르지 않을 경우
        doThrow(new CustomException(UserExceptionType.USER_DOES_NOT_EXIST))
                .when(applicationService)
                .submitApplication(2L, 1L);

        mockMvc.perform(
                        post("/wanted/applications")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andExpect(status().isNotFound())
                .andDo(print());

    }
}
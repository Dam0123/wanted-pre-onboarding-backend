package com.wanted.application.service;

import com.wanted.application.domain.Application;
import com.wanted.application.repository.ApplicationRepository;
import com.wanted.exception.ApplicationExceptionType;
import com.wanted.exception.CustomException;
import com.wanted.post.domain.Post;
import com.wanted.post.service.PostService;
import com.wanted.user.domain.User;
import com.wanted.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final PostService postService;
    private final UserService userService;

    //채용공고 지원하기
    public void submitApplication(Long userId, Long postId) {
       Post post = postService.validateExistPost(postId);
       User user = userService.validateExistUser(userId);

       if (hasAlreadyApplied(user, post)) {
           throw new CustomException(ApplicationExceptionType.ALREADY_APPLIED);
       }
       else {
           Application application = Application.builder()
                   .user(user)
                   .post(post)
                   .build();
           applicationRepository.save(application);
       }
    }

    private boolean hasAlreadyApplied(User user, Post post) {
        return applicationRepository.existsByUserAndPost(user, post);
    }

}
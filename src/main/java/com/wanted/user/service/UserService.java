package com.wanted.user.service;

import com.wanted.exception.CustomException;
import com.wanted.exception.UserExceptionType;
import com.wanted.user.domain.User;
import com.wanted.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User validateExistUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserExceptionType.USER_DOES_NOT_EXIST));
    }
}

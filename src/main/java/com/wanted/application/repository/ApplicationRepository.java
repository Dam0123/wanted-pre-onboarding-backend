package com.wanted.application.repository;

import com.wanted.application.domain.Application;
import com.wanted.post.domain.Post;
import com.wanted.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByUserAndPost(User user, Post post);
}

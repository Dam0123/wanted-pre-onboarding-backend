package com.wanted.post.domain;

import com.wanted.company.domain.Company;
import com.wanted.post.dto.PostRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer reward;

    @Column(nullable = false)
    private String skill;

    @Column(nullable = false)
    private String deadline;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;


    @Builder
    public Post(Long id, String position, String description, Integer reward, String skill, String deadline, Company company) {
        this.id = id;
        this.position = position;
        this.description = description;
        this.reward = reward;
        this.skill = skill;
        this.deadline = deadline;
        this.company = company;
    }

    public void updatePostInfo(Long postId, PostRequest target){
        this.position = target.getPosition();
        this.description = target.getDescription();
        this.reward = target.getReward();
        this.skill = target.getSkill();
        this.deadline = target.getDeadline();
    }

}

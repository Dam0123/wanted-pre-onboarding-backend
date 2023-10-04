package com.wanted.post.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wanted.post.dto.PostListResponse;
import com.wanted.post.dto.QPostListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.List;

import static com.wanted.company.domain.QCompany.company;
import static com.wanted.post.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<PostListResponse> findByKeyword(String keyword, Pageable pageable) {
        BooleanBuilder whereClause = eqEachConditions(keyword);

        List<PostListResponse> postListResponses = queryFactory
                .select(new QPostListResponse(
                        post.id,
                        post.position,
                        post.reward,
                        post.skill,
                        post.company.companyName,
                        post.company.country,
                        post.company.region
                ))
                .from(post)
                .innerJoin(post.company, company)
                .where(whereClause)
                .orderBy(post.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        List<PostListResponse> content = new ArrayList<>();
        for (PostListResponse postListResponse : postListResponses) {
            content.add(PostListResponse.of(postListResponse));
        }

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(content, pageable, hasNext);
    }

    private BooleanBuilder eqEachConditions(String keyword) {
        return new BooleanBuilder()
                .or(eqPosition(keyword))
                .or(eqSkill(keyword))
                .or(eqCompanyName(keyword))
                .or(eqCountry(keyword))
                .or(eqRegion(keyword));
    }

    private BooleanExpression eqPosition(String keyword) {
        return keyword == null ? null : post.position.contains(keyword);
    }

    private BooleanExpression eqSkill(String keyword) {
        return keyword == null ? null : post.skill.contains(keyword);
    }

    private BooleanExpression eqCompanyName(String keyword) {
        return keyword == null ? null : post.company.companyName.contains(keyword);
    }

    private BooleanExpression eqCountry(String keyword) {
        return keyword == null ? null : post.company.country.contains(keyword);
    }

    private BooleanExpression eqRegion(String keyword) {
        return keyword == null ? null : post.company.region.contains(keyword);
    }

}

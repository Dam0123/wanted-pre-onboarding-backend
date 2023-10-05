package com.wanted.post.dto;

import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
public class PostSliceResponses<T> {
    private List<T> posts;
    private Pageable pageable;
    private boolean first;
    private boolean last;


    public PostSliceResponses(List<T> posts, Slice<T> slicePageable) {
        this.posts = posts;
        this.pageable = slicePageable.getPageable();
        this.first = slicePageable.isFirst();
        this.last = slicePageable.isLast();
    }
}

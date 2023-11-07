package com.gdsc_teama.servertoyproject.dto.post;

import com.gdsc_teama.servertoyproject.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String postTitle;
    private String postContent;

    @Builder
    public PostUpdateRequestDto(String postTitle, String postContent) {
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public Post toEntity() {
        return Post.builder()
                .postTitle(postTitle)
                .postContent(postContent)
                .build();
    }
}

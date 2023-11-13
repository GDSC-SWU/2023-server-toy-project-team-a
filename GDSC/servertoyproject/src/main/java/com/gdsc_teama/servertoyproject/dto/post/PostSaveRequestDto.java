package com.gdsc_teama.servertoyproject.dto.post;

import com.gdsc_teama.servertoyproject.entity.Post;
import com.gdsc_teama.servertoyproject.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private User user;
    private String postTitle;
    private String postContent;

    @Builder
    public PostSaveRequestDto(User user, String postTitle, String postContent) {
        this.user = user;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public Post toEntity() {
        return Post.builder()
                .user(user)
                .postTitle(postTitle)
                .postContent(postContent)
                .build();
    }
}

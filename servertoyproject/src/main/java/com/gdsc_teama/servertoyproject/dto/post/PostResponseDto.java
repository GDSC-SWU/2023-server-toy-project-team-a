package com.gdsc_teama.servertoyproject.dto.post;

import com.gdsc_teama.servertoyproject.entity.Post;
import com.gdsc_teama.servertoyproject.entity.User;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private final Long id;
    private final User user;
    private final String postTitle;
    private final String postContent;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.user = post.getUser();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
    }
}

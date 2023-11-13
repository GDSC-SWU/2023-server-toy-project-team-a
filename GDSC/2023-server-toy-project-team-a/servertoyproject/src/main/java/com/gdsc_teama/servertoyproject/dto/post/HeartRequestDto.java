package com.gdsc_teama.servertoyproject.dto.post;

import com.gdsc_teama.servertoyproject.entity.Post;
import com.gdsc_teama.servertoyproject.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeartRequestDto {
    private Post postId;
    private User userId;

    @Builder
    public HeartRequestDto(User userId, Post postId){
        this.postId = postId;
        this.userId = userId;
    }
}
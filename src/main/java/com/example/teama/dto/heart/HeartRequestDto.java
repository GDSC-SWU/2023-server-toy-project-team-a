package com.example.teama.dto.heart;

import com.example.teama.entity.Post;
import com.example.teama.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeartRequestDto {
    private Long postId;
    private Long userId;

    @Builder
    public HeartRequestDto(Long userId, Long postId){
        this.postId = postId;
        this.userId = userId;
    }
}
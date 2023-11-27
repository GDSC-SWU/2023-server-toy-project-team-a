package com.example.teama.controller;

import com.example.teama.dto.post.PostResponseDto;
import com.example.teama.dto.post.PostSaveRequestDto;
import com.example.teama.service.UserHeartPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/my-page")
public class UserHeartPostController {   // 내가 좋아요한 게시글 보기

    private final UserHeartPostService userHeartPostService;

    @GetMapping("/heart-post")
    public List<PostResponseDto> getHeartPost(@PathVariable Long id) {
        return userHeartPostService.findHeartPost(id); // userHeartPostService를 통해 user가 좋아요한 게시글을 조회한 결과 반환
    }
}
package com.gdsc_teama.servertoyproject.controller;

import com.gdsc_teama.servertoyproject.dto.post.HeartRequestDto;
import com.gdsc_teama.servertoyproject.entity.Heart;
import com.gdsc_teama.servertoyproject.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class HeartController {
    private HeartService heartService;

    // 좋아요 추가
    // HeartService를 이용하여 좋아요 추가
    @PutMapping("/api/v1/post/{postid}")
    public String addHeart(@RequestBody HeartRequestDto requestDto) {
        heartService.addHeart(requestDto); //addHeart 메서드 호출하여 좋아요 추가
        return "Add Heart";
    }

    // 좋아요 삭제
    // HeartService를 이용하여 좋아요 삭제
    @DeleteMapping("/api/v1/post/{postId}")
    public String removeHeart(@RequestBody HeartRequestDto requestDto) {
        heartService.removeHeart(requestDto); //removeHeart 메서드 호출하여 좋아요 삭제
        return "Delete Heart";
    }
}
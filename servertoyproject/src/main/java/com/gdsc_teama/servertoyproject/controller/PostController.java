package com.gdsc_teama.servertoyproject.controller;

import com.gdsc_teama.servertoyproject.dto.post.PostSaveRequestDto;
import com.gdsc_teama.servertoyproject.dto.post.PostUpdateRequestDto;
import com.gdsc_teama.servertoyproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    // Save Post
    @PostMapping("/api/v1/post")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    // Update Post
    @PutMapping("/api/v1/post/{postId}")
    public Long update(@PathVariable Long postId, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(postId, requestDto);
    }
}

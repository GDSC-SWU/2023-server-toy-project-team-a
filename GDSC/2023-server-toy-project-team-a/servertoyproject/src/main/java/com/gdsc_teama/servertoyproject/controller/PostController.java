package com.gdsc_teama.servertoyproject.controller;

import com.gdsc_teama.servertoyproject.dto.post.PostResponseDto;
import com.gdsc_teama.servertoyproject.dto.post.PostSaveRequestDto;
import com.gdsc_teama.servertoyproject.dto.post.PostUpdateRequestDto;
import com.gdsc_teama.servertoyproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/api/v1/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    // Read Post
    @GetMapping("/api/v1/post/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    // Read Post List
    @GetMapping("/api/v1/post")
    public List<PostResponseDto> findAllDesc() {
        return postService.findAllDesc();
    }

    // Delete Post
    @DeleteMapping("/api/v1/post/{postId}")
    public Long delete(@PathVariable Long postId) {
        postService.delete(postId);

        return postId;
    }
}
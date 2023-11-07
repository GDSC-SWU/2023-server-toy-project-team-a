package com.gdsc_teama.servertoyproject.service;

import com.gdsc_teama.servertoyproject.dto.post.PostSaveRequestDto;
import com.gdsc_teama.servertoyproject.dto.post.PostUpdateRequestDto;
import com.gdsc_teama.servertoyproject.entity.Post;
import com.gdsc_teama.servertoyproject.persistence.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    // Save Post
    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    // Update Post
    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        post.update(requestDto.getPostTitle(), requestDto.getPostContent());
        return id;  // post id return
    }

    // Post List
}

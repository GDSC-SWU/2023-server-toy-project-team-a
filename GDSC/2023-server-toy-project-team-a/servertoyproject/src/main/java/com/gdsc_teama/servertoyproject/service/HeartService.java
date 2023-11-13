package com.gdsc_teama.servertoyproject.service;

import com.gdsc_teama.servertoyproject.dto.post.HeartRequestDto;
import com.gdsc_teama.servertoyproject.entity.Heart;
import com.gdsc_teama.servertoyproject.entity.Post;
import com.gdsc_teama.servertoyproject.entity.User;
import com.gdsc_teama.servertoyproject.persistence.HeartRepository;
import com.gdsc_teama.servertoyproject.persistence.PostRepository;
import com.gdsc_teama.servertoyproject.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class HeartService {

    private HeartRepository heartRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;


    // 좋아요 추가
    public void addHeart(HeartRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId().getId())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));      // HeartRequestDto에서 userId를 가져와서 UserRepository에서 user 탐색
        Post post = postRepository.findById(requestDto.getPostId().getId())
                .orElseThrow(() -> new RuntimeException("게시물 없음"));      // HeartRequestDto에서 postId를 가져와서 PostRepository에서 post 탐색
        Heart heart = Heart.builder().user(user).post(post).build();        // user와 post를 찾았다면 Heart 엔터티를 생성합니다.
        heartRepository.save(heart);  // 생성된 Heart 엔터티를 HeartRepository를 통해 저장
    }

    // 좋아요 취소
    public void removeHeart(HeartRequestDto requestDto) {
        heartRepository.deleteByUserIdAndPostId(requestDto.getUserId(), requestDto.getPostId());    // HeartRequestDto에서 userId와 postId를 가져와서 해당하는 좋아요 삭제
    }
}
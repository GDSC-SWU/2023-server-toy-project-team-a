package com.example.teama.service;

import com.example.teama.dto.heart.HeartRequestDto;
import com.example.teama.entity.Heart;
import com.example.teama.entity.Post;
import com.example.teama.entity.User;
import com.example.teama.persistence.HeartRepository;
import com.example.teama.persistence.PostRepository;
import com.example.teama.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {

    private HeartRepository heartRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;


    // 좋아요 추가
    public void addHeart(HeartRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new RuntimeException("사용자 없음"));
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(() -> new RuntimeException("게시물 없음"));
        if (heartRepository.findByUserAndPost(user, post).isPresent()){
            throw new RuntimeException("사용자가 이미 해당 게시물을 좋아합니다.");
        }
        Heart heart = Heart.builder().user(user).post(post).build();
        heartRepository.save(heart);
    }
    // HeartRequestDto에서 userId를 가져와서 UserRepository에서 user 탐색, 없으면 예외반환
    // HeartRequestDto에서 postId를 가져와서 PostRepository에서 post 탐색
    // user가 이미 좋아요를 했다면 문구 반환
    // user와 post를 찾았다면 Heart 엔터티를 생성합니다.
    // 생성된 Heart 엔터티를 HeartRepository를 통해 저장

    // 좋아요 취소
    public void deleteHeart(HeartRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new RuntimeException("사용자 없음"));
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(() -> new RuntimeException("게시물 없음"));
        Heart heart = heartRepository.findByUserAndPost(user, post).orElseThrow(() -> new RuntimeException("좋아요 찾을 수 없음"));
        heartRepository.deleteByUserIdAndPostId(requestDto.getUserId(), requestDto.getPostId());
        // HeartRequestDto에서 userId와 postId를 가져와서 해당하는 좋아요 삭제
    }
}
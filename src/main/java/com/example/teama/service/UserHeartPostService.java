package com.example.teama.service;

import com.example.teama.dto.post.PostResponseDto;
import com.example.teama.entity.Heart;
import com.example.teama.persistence.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserHeartPostService {
    private final HeartRepository heartRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> findHeartPost(Long id) { //user가 좋아요한 게시글을 조회
        Optional<Heart> userHeart = heartRepository.findById(id); //HeartRepository를 사용하여 user의 좋아요 정보를 조회

        return userHeart.stream()
                .map(heart -> new PostResponseDto(heart.getPost()))
                .collect(Collectors.toList());
    }
}
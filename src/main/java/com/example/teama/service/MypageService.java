package com.example.teama.service;

import com.example.teama.dto.user.UserInfoDto;
import com.example.teama.dto.user.UserResponseDto;
import com.example.teama.dto.user.UserUpdateRequestDto;
import com.example.teama.entity.User;
import com.example.teama.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final UserRepository userRepository;

    // 내 정보 조회
    @Transactional
    public UserInfoDto read(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자 없음. id"+ id));
        return new UserInfoDto(id, user.getUserEmail(), user.getUserPassword(), user.getUserPhone());
    }



    // 내 정보 수정
    @Transactional
    public UserUpdateRequestDto update(Long id, UserUpdateRequestDto requestDto){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자 없음. id"+ id));
        user.update(requestDto.getUserPhone(), requestDto.getUserNickname());
        return UserUpdateRequestDto.builder().userPhone("aa1234").userNickname("01012345678").build();
    }
    // userRepository.findById(id), userRepository에서 사용자를 조회
    // findById의 결과가 null인 경우, orElseThrow 메서드를 사용하여 IllegalArgumentException을 던져 예외를 발생시킴
    // user가 조회되면, User 엔터티의 update 메서드를 호출하여 사용자의 정보를 업데이트, UserUpdateRequestDto에서 받아온 정보로 User 엔터티의 필드를 업데이트하는 역할 수행
    // update 성공시에 update 값 리턴
}
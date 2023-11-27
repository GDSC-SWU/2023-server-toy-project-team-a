package com.example.teama.controller;

import com.example.teama.dto.user.UserInfoDto;
import com.example.teama.dto.user.UserResponseDto;
import com.example.teama.dto.user.UserUpdateRequestDto;
import com.example.teama.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/my-page")
public class MypageController {

    private final MypageService mypageService;

    // 내 정보 조회
    @GetMapping("/{id}")
    public UserInfoDto read(@PathVariable Long id) {
        return mypageService.read(id); // MypageService의 read 메서드를 호출하여 user 정보 불러오기
    }

    // 내 정보 수정
    @PutMapping("/update/{id}")
    public UserUpdateRequestDto update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto) {
        return mypageService.update(id,requestDto); //사용자 id와 UserUpdateRequestDto를 이용하여 사용자 정보를 업데이트하고, 업데이트된 user의 id 반환
    }
}
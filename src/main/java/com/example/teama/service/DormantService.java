package com.example.teama.service;

import com.example.teama.entity.User;
import com.example.teama.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DormantService { //휴면회원전환을 위한 클래스

    private final UserRepository userRepository;

    @Transactional
    public void DormantUser(Long userId) {
        User user = userRepository.findById(userId) // userId를 찾음
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없음. userId: " + userId));

        if (!user.isUserStatus()) {
            throw new IllegalStateException("이미 휴면 회원입니다. userId: " + userId);
        }

            // 사용자의 마지막 로그인 시간을 가져옵니다.
            LocalDateTime lastLoginTime = user.getUserUpdateDate();

            // 1년 이상 로그인이 없을 경우 휴면 회원으로 전환합니다.
            if (lastLoginTime != null && lastLoginTime.isBefore(LocalDateTime.now().minusYears(1))) {
                user.setUserStatus(false);  // 회원 상태를 비활성화(휴면 회원으로 변경)
                user.setUserUpdateDate(LocalDateTime.now());  // 회원 정보 업데이트 날짜 갱신
                userRepository.save(user);  // 변경된 정보를 저장
        }
    }
}
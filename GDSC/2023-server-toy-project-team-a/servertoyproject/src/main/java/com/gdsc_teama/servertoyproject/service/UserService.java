package com.gdsc_teama.servertoyproject.service;

import com.gdsc_teama.servertoyproject.entity.User;
import com.gdsc_teama.servertoyproject.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    // 전체 user 정보 조회
    // 이메일로 사용자 조회
    public User getUserByEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }
    // 비밀번호로 사용자 조회
    public User getUserByPassword(String userPassword) {
        return userRepository.findByUserPassword(userPassword);
    }

    // 전화번호로 사용자 조회
    public User getUserByPhone(String userPhone) {
        return userRepository.findByUserPhone(userPhone);
    }

    // 닉네임으로 사용자 조회
    public User getUserByNickname(String userNickname) {
        return userRepository.findByUserNickname(userNickname);
    }
}
package com.gdsc_teama.servertoyproject.controller;

import com.gdsc_teama.servertoyproject.entity.User;
import com.gdsc_teama.servertoyproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private UserService userService;

    // 전체 user 정보 조회
    @GetMapping("/api/v1/user/{userEmail}")
    public User getUserByEmail(@PathVariable String userEmail) {
        return userService.getUserByEmail(userEmail);
    }

    @GetMapping("/api/v1/user/{userPassword}")
    public User getUserByPassword(@PathVariable String userPassword) {
        return userService.getUserByPassword(userPassword);
    }

    @GetMapping("/api/v1/user/{userPhone}")
    public User getUserByPhone(@PathVariable String userPhone) {
        return userService.getUserByPhone(userPhone);
    }

    @GetMapping("/api/v1/user/{userNickname}")
    public User getUserByNickname(@PathVariable String userNickname) {
        return userService.getUserByNickname(userNickname);
    }
}
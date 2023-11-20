package com.example.teama.controller;

import com.example.teama.service.DormantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DormantController {

    private final DormantService dormantService;

    @PostMapping("/api/v1/dormant/{userId}")
    public String DormantUser(@PathVariable Long userId) {
        dormantService.DormantUser(userId);
        return "사용자 휴면회원 전환 완료";
    }
}
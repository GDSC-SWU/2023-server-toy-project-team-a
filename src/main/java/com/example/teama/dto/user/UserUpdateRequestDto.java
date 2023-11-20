package com.example.teama.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto { //userPhone과 userNickname 업데이트를 위한 dto
    private String userPhone;
    private String userNickname;

    @Builder
    public UserUpdateRequestDto(String userPhone, String userNickname){
        this.userPhone=userPhone;
        this.userNickname=userNickname;
    }
}
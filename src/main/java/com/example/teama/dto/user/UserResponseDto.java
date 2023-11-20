package com.example.teama.dto.user;

import com.example.teama.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto { // 내 정보 조회만을 위한 dto
    private Long id;
    private String userEmail;
    private String userPassword;
    private String userPhone;
    private String userNickname;

    public UserResponseDto(User entity){
        this.id = entity.getId();
        this.userEmail = entity.getUserEmail();
        this.userPassword = entity.getUserPassword();
        this.userPhone = entity.getUserPhone();
        this.userNickname = entity.getUserNickname();
    }
}
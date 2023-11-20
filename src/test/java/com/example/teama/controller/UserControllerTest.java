package com.example.teama.controller;

import com.example.teama.dto.user.UserUpdateRequestDto;
import com.example.teama.entity.User;
import com.example.teama.persistence.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @DisplayName("myInfoInquiry")
    public void myInfoInquiry() throws Exception {

        // user정보생성
        User user = User.builder()
                .userEmail("email")
                .userPassword("password")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        userRepository.save(user);

        mockMvc.perform(get("/api/v1/mypage")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("myInfoUpdate")
    public void myInfoUpdate() throws Exception {
        // 저장되어 있는 user 정보생성
        User savedUser = userRepository.save(User.builder()
                .userEmail("email")
                .userPassword("password")
                .userPhone("phone")
                .userNickname("nickname")
                .build());

        // user의 id를 찾고
        Long updateId = savedUser.getId();
        String expectedPhone = "01012345678";
        String expectedNickname = "bbb";

        // 새로운 값을 업데이트하고
        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder()
                .userPhone(expectedPhone)
                .userNickname(expectedNickname)
                .build();

        String url = "/api/v1/mypage/{id}";
        HttpEntity<UserUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        mockMvc.perform(put(url, updateId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                        .andExpect(status().isOk());

        // 업데이트 후 사용자 정보를 조회하고 예상 값과 일치하는지 확인
        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getUserPhone()).isEqualTo(expectedPhone);
        assertThat(all.get(0).getUserNickname()).isEqualTo(expectedNickname);
    }
}
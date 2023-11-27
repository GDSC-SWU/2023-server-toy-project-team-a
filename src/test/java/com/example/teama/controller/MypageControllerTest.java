package com.example.teama.controller;

import com.example.teama.dto.user.UserInfoDto;
import com.example.teama.dto.user.UserUpdateRequestDto;
import com.example.teama.entity.User;
import com.example.teama.persistence.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MypageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @DisplayName("myInfoInquiry")
    public void myInfoInquiry() throws Exception {

        // user정보생성
        User user = User.builder()
                .userEmail("email")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        userRepository.save(user);

        Long userId = user.getId();

        mockMvc.perform(get("/api/v1/my-page/", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("myInfoUpdate")
    public void myInfoUpdate() throws Exception {

        User user = User.builder()
                .userEmail("email")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        userRepository.save(user);

        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder()
                .userPhone("phone2")
                .userNickname("nickname2")
                .build();

        String json = objectMapper.writeValueAsString(requestDto);
        Long updateId = user.getId();

        mockMvc.perform(put("/api/v1/my-page/update/",updateId)
                        .contentType(APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isOk())
                        .andDo(print());
    }
}
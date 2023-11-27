package com.example.teama.controller;

import com.example.teama.entity.User;
import com.example.teama.persistence.UserRepository;
import com.example.teama.service.DormantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DormantControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void dormantUser() throws Exception {

        /*User savedUser = User.builder()
                .userEmail("email")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        savedUser.setUserStatus(true);
        savedUser.setUserUpdateDate(LocalDateTime.now().minusYears(1));

        Long userId = 1L;

        //given(userRepository.findById(userId)).willReturn(Optional.of(savedUser));

        mockMvc.perform(post("/api/v1/dormant/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());*/
    }
}
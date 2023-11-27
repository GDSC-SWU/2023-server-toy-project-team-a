package com.example.teama.controller;

import com.example.teama.dto.heart.HeartRequestDto;
import com.example.teama.entity.Post;
import com.example.teama.entity.User;
import com.example.teama.persistence.HeartRepository;
import com.example.teama.persistence.PostRepository;
import com.example.teama.persistence.UserRepository;
import com.example.teama.service.HeartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HeartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private HeartRepository heartRepository;
    @Autowired
    private HeartService heartService;

    @Test
    @Transactional
    public void addHeart() throws Exception {

        User savedUser = userRepository.save(User.builder().build());

        Post savedPosts = postRepository.save(Post.builder().build());

        HeartRequestDto requestDto = HeartRequestDto.builder()
                .userId(savedUser.getId())
                .postId(savedPosts.getId())
                .build();

        mockMvc.perform(post("/api/v1/heart/{postId}", requestDto.getPostId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                        .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void deleteHeart() throws Exception {

        User savedUser = userRepository.save(User.builder().build());

        Post savedPosts = postRepository.save(Post.builder().build());

        HeartRequestDto requestDto = HeartRequestDto.builder()
                .userId(savedUser.getId())
                .postId(savedPosts.getId())
                .build();

        mockMvc.perform(delete("/api/v1/heart/{postId}", requestDto.getPostId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                        .andExpect(status().isOk());
    }
}
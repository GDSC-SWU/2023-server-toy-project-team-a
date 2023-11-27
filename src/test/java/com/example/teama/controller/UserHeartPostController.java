package com.example.teama.controller;


import com.example.teama.entity.Post;
import com.example.teama.entity.User;
import com.example.teama.persistence.HeartRepository;
import com.example.teama.persistence.PostRepository;
import com.example.teama.persistence.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.reflect.Array.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserHeartPostController { // 내가 좋아요한 게시물 조회

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private HeartRepository heartRepository;

    @Test
    @Transactional
    public void findHeartPost() throws Exception {

        User user = User.builder().build();

        userRepository.save(user);

        Post savedPosts = postRepository.save(Post.builder().build());

        Long getPostId = savedPosts.getId();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/my-page/heart-post", getPostId)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());
    }
}
package com.example.teama.controller;

import com.example.teama.dto.Reply.ReplyActionDto;
import com.example.teama.dto.Reply.ReplySaveDto;
import com.example.teama.entity.Reply;
import com.example.teama.entity.User;
import com.example.teama.persistence.ReplyRepository;
import com.example.teama.persistence.UserRepository;
import com.example.teama.service.ReplyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserRepository userRepository; // UserRepository 주입

    @Autowired
    private ReplyRepository replyRepository; // ReplyRepository 주입

    @Test
    @Transactional
    @DisplayName("getRepliesByPostId() 테스트")
    public void getRepliesByPostId() throws Exception {
        // 게시글 ID에 해당하는 댓글 조회 테스트
        // 이 부분은 실제 데이터베이스에 데이터가 있는지에 따라 수정이 필요할 수 있습니다.
        mockMvc.perform(get("/replies/post/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("saveReply() 테스트")
    public void saveReply() throws Exception {
        // 댓글 저장 테스트
        User user = User.builder()
                .userEmail("test@example.com")
                .userPassword("testPassword")
                .userPhone("1234567890")
                .userNickname("testUser")
                .build();

        ReplySaveDto requestDto = ReplySaveDto.builder()
                .user(user)
                .replyContent("Test Reply Content")
                .build();

        String content = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/replies")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("updateReply() 테스트")
    public void updateReply() throws Exception {

        // 1. User 객체 생성 및 저장
        User user = User.builder()
                .userEmail("email")
                .userPassword("password")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        userRepository.save(user);

        // 2. 댓글 생성 및 저장
        Reply savedReply = replyRepository.save(Reply.builder()
                .user(user)
                .replyContent("content")
                .build());

        // 3. 저장된 댓글의 ID 얻기
        Long replyId = savedReply.getId();

        // 4. 댓글 수정을 위한 DTO 생성
        ReplyActionDto requestDto = ReplyActionDto.builder()
                .replyContent("Updated Reply Content")
                .build();

        // 5. DTO를 JSON으로 변환
        String content = objectMapper.writeValueAsString(requestDto);

        // 6. 수정 요청 수행
        mockMvc.perform(put("/replies/{replyId}", replyId)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @Transactional
    @DisplayName("findById() 테스트")
    public void findReplyById() throws Exception {
        User user = User.builder()
                .userEmail("email")
                .userPassword("password")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        userRepository.save(user);

        Reply savedReply = replyRepository.save(Reply.builder()
                .user(user)
                .replyContent("content")
                .build());

        Long replyId = savedReply.getId();

        mockMvc.perform(get("/replies/{id}", replyId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("findAllDesc() 테스트")
    public void findAllRepliesDesc() throws Exception {
        User user = User.builder()
                .userEmail("email")
                .userPassword("password")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        userRepository.save(user);

        Reply savedReply1 = replyRepository.save(Reply.builder()
                .user(user)
                .replyContent("content1")
                .build());

        Reply savedReply2 = replyRepository.save(Reply.builder()
                .user(user)
                .replyContent("content2")
                .build());

        mockMvc.perform(get("/replies")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("deleteReply() 테스트")
    public void deleteReply() throws Exception {
        // 댓글 삭제 테스트
        User user = User.builder()
                .userEmail("email")
                .userPassword("password")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        userRepository.save(user);

        Reply savedReply = replyRepository.save(Reply.builder()
                .user(user)
                .replyContent("content")
                .build());

        Long replyId = savedReply.getId();

        mockMvc.perform(delete("/replies/{replyId}", replyId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
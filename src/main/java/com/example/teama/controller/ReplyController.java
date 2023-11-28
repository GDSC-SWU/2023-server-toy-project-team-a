package com.example.teama.controller;

import com.example.teama.dto.Reply.ReplyActionDto;
import com.example.teama.dto.Reply.ReplySaveDto;
import com.example.teama.entity.User;
import com.example.teama.jwt.util.IfLogin;
import com.example.teama.jwt.util.LoginUserDto;
import com.example.teama.service.PostService;
import com.example.teama.service.ReplyService;
import com.example.teama.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
@Service

public class ReplyController {

    private final ReplyService replyService;
    private final PostService postService;
    private final UserService userService;

    // 게시글 ID에 해당하는 모든 댓글 조회
    @GetMapping("/post/{id}")
    public List<ReplyActionDto> getRepliesByPostId(@PathVariable Long id) {
        log.info("조회 완료");
        return replyService.findRepliesByPostId(id);
    }

    // 댓글 저장
    @PostMapping()
    public Long saveReply(@IfLogin LoginUserDto loginUserDto, @RequestBody ReplySaveDto requestDto) {
        User user = userService.findByEmail(loginUserDto.getUserEmail());
        requestDto.setUser(user);

        return replyService.saveReply(requestDto);
    }



    // 댓글 작성
    @PutMapping("/{replyId}")
    public void updateReply(@PathVariable Long replyId, @RequestBody ReplyActionDto requestDto) {
        // 수정된 내용을 가지고 있는 DTO를 사용해 특정 댓글 작성
        replyService.updateReply(replyId, requestDto);
    }


    // 댓글 삭제
    @DeleteMapping("/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
    }

}
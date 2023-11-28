package com.example.teama.dto.Reply;

import com.example.teama.entity.Post;
import com.example.teama.entity.Reply;
import com.example.teama.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ReplySaveRequestDto {
    private String content; // 댓글 내용
    private User user; // 댓글 작성자 정보 (사용자 ID)
    private Post post; // 댓글이 속한 게시물
    private String ipAddress; // 댓글 작성자의 IP 주소
    private String userAgent; // 댓글 작성자의 User Agent 정보

    @Builder
    public ReplySaveRequestDto(String content, User user, Post post, String ipAddress, String userAgent) {
        this.content = content;
        this.user = user;
        this.post = post;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

    public Reply toEntity() {
        return Reply.builder()
                .replyContent(content)
                .user(user)
                .post(post)
                .build();
    }
}
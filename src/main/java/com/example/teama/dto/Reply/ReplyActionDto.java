package com.example.teama.dto.Reply;

import com.example.teama.entity.Post;
import com.example.teama.entity.Reply;
import com.example.teama.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ReplyActionDto {
    private String replyContent; // 댓글 내용
    private User user; // 댓글 작성자 정보
    private Post post; // 댓글이 속한 게시물

    public ReplyActionDto(Reply reply) {
        this.user = reply.getUser();
        this.post = reply.getPost();
        this.replyContent = reply.getReplyContent();
    }
}
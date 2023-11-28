package com.example.teama.dto.Reply;

import com.example.teama.entity.Post;
import com.example.teama.entity.Reply;
import com.example.teama.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplySaveDto {
    @Getter @Setter private String replyContent; // 댓글 내용
    @Getter @Setter private User user; // 댓글 작성자 정보
    @Getter @Setter private Post post; // 댓글이 속한 게시물


    public Reply toEntity() {
        return Reply.builder()
                .replyContent(replyContent)
                .user(user)
                .post(post)
                .build();
    }

    public Long getPostId() {
        // 게시글 ID에 해당하는 메서드를 getPostId로 변경
        return post.getId();
    }


}
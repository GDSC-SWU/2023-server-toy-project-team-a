package com.example.teama.persistence;

import com.example.teama.entity.Post;
import com.example.teama.entity.Reply;
import com.example.teama.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveReply() {
        // given
        User user = User.builder()
                .userEmail("email")
                .userPassword("password")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        userRepository.save(user);

        Post post = Post.builder()
                .postTitle("Post Title")
                .postContent("Post Content")
                .user(user)
                .build();

        postRepository.save(post);

        String replyContent = "Reply Content";

        Reply reply = Reply.builder()
                .replyContent(replyContent)
                .post(post)
                .user(user)
                .build();

        // when
        replyRepository.save(reply);
        Optional<Reply> savedReply = replyRepository.findById(reply.getId());

        // then
        assertThat(savedReply).isPresent();
        assertThat(savedReply.get().getReplyContent()).isEqualTo(replyContent);
        assertThat(savedReply.get().getPost().getId()).isEqualTo(post.getId());
        assertThat(savedReply.get().getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    public void findByPost() {
        // given
        User user = User.builder()
                .userEmail("email")
                .userPassword("password")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        userRepository.save(user);

        Post post = Post.builder()
                .postTitle("Post Title")
                .postContent("Post Content")
                .user(user)
                .build();

        postRepository.save(post);

        String replyContent = "Reply Content";

        Reply reply = Reply.builder()
                .replyContent(replyContent)
                .post(post)
                .user(user)
                .build();

        replyRepository.save(reply);

        // when
        List<Reply> replies = replyRepository.findByPost(post);

        // then
        assertThat(replies).hasSize(1);
        assertThat(replies.get(0).getReplyContent()).isEqualTo(replyContent);
        assertThat(replies.get(0).getPost().getId()).isEqualTo(post.getId());
        assertThat(replies.get(0).getUser().getId()).isEqualTo(user.getId());
    }
}
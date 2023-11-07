package com.gdsc_teama.servertoyproject.repository;

import com.gdsc_teama.servertoyproject.entity.Post;
import com.gdsc_teama.servertoyproject.entity.User;
import com.gdsc_teama.servertoyproject.persistence.PostRepository;
import com.gdsc_teama.servertoyproject.persistence.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @After  // 단위테스트가 끝날 때마다 수행되는 메서드 지정
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장() {
        // given
        User user = User.builder()
                .userEmail("email")
                .userPassword("password")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        userRepository.save(user);

        String postTitle = "title";
        String postContent = "content";

        postRepository.save(Post.builder()
                .postTitle(postTitle)
                .postContent(postContent)
                .user(user)
                .build());

        // when
        List<Post> postList = postRepository.findAll();

        // then
        Post post = postList.get(0);
        assertThat(post.getPostTitle()).isEqualTo(postTitle);
        assertThat(post.getUser().getId()).isEqualTo(user.getId());
        assertThat(post.getPostContent()).isEqualTo(postContent);
    }
}

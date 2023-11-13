package com.gdsc_teama.servertoyproject.controller;

import com.gdsc_teama.servertoyproject.dto.post.PostSaveRequestDto;
import com.gdsc_teama.servertoyproject.dto.post.PostUpdateRequestDto;
import com.gdsc_teama.servertoyproject.entity.Post;
import com.gdsc_teama.servertoyproject.entity.User;
import com.gdsc_teama.servertoyproject.persistence.PostRepository;
import com.gdsc_teama.servertoyproject.persistence.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void Post_등록() throws Exception {
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

        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                .user(user)
                .postTitle(postTitle)
                .postContent(postContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/post";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getUser().getId()).isEqualTo(user.getId());
        assertThat(all.get(0).getPostTitle()).isEqualTo(postTitle);
        assertThat(all.get(0).getPostContent()).isEqualTo(postContent);
    }

    @Test
    public void Posts_수정() throws Exception {
        // given
        User user = User.builder()
                .userEmail("email")
                .userPassword("password")
                .userPhone("phone")
                .userNickname("nickname")
                .build();

        userRepository.save(user);

        Post savedPosts = postRepository.save(Post.builder()
                .user(user)
                .postTitle("title")
                .postContent("content")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .postTitle(expectedTitle)
                .postContent(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/post/" + updateId;

        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.
                exchange(url, HttpMethod.PUT,
                        requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getPostTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getPostContent()).isEqualTo(expectedContent);
    }
}

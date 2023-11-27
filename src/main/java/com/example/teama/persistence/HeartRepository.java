package com.example.teama.persistence;

import com.example.teama.entity.Heart;
import com.example.teama.entity.Post;
import com.example.teama.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface HeartRepository extends JpaRepository<Heart, Long> {
    @Modifying
    @Query("DELETE FROM Heart h WHERE h.user.id = :userId AND h.post.id = :postId")
    void deleteByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);

    Optional<Heart> findByUserAndPost(User user, Post post);
}
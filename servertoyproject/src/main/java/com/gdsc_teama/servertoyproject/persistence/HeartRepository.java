package com.gdsc_teama.servertoyproject.persistence;

import com.gdsc_teama.servertoyproject.entity.Heart;
import com.gdsc_teama.servertoyproject.entity.Post;
import com.gdsc_teama.servertoyproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HeartRepository extends JpaRepository<Heart, Long> {
    void deleteByUserIdAndPostId(User userId, Post postId);
}
package com.gdsc_teama.servertoyproject.persistence;

import com.gdsc_teama.servertoyproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findAllByOrderByIdDesc();    // Using JPA Naming Convention
}

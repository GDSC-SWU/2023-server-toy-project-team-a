package com.gdsc_teama.servertoyproject.persistence;

import com.gdsc_teama.servertoyproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String userEmail);
    User findByUserNickname(String userNickname);
    User findByUserPhone(String userPhone);
    User findByUserPassword(String userPassword);
}
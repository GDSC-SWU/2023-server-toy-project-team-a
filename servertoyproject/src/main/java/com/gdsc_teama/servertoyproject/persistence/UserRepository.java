package com.gdsc_teama.servertoyproject.persistence;

import com.gdsc_teama.servertoyproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

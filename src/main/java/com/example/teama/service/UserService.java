package com.example.teama.service;

import com.example.teama.dto.user.UserResponseDto;
import com.example.teama.dto.user.UserUpdateRequestDto;
import com.example.teama.entity.Role;
import com.example.teama.entity.User;
import com.example.teama.persistence.RoleRepository;
import com.example.teama.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public User findByEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
    }

    @Transactional
    public User addUser(User User) {
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        User.addRole(userRole.get());
        User saveUser = userRepository.save(User);
        return saveUser;
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(Long UserId){
        return userRepository.findById(UserId);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(String email){
        return userRepository.findByUserEmail(email);
    }
}
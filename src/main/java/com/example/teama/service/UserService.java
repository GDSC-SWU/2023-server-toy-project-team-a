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




    // 내 정보 조회
    @Transactional(readOnly = true) // 데이터를 읽는 성능을 향상시키기 위해 @Transactional(readOnly = true)사용
    public List<UserResponseDto> findAllDesc() {
        return userRepository.findAllDesc().stream().map(UserResponseDto::new).collect(Collectors.toList());
        // findAllDesc()가 호출될 때 User 메소드의 파라미터를 전달받는다
        // UserRepository에서 정의된 findAllDesc() 메서드를 호출하여 user를 조회
        // .stream().map(UserResponseDto::new)으로 조회된 user 리스트를 stream으로 변환하고, 매핑된 UserResponseDto로 변환하여 list로 반환
    }

    // 내 정보 수정
    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자 없음. id"+ id));
        user.update(requestDto.getUserPhone(), requestDto.getUserNickname());
        return id;
        // userRepository.findById(id), userRepository에서 사용자를 조회
        // findById의 결과가 null인 경우, orElseThrow 메서드를 사용하여 IllegalArgumentException을 던져 예외를 발생시킴
        // user가 조회되면, User 엔터티의 update 메서드를 호출하여 사용자의 정보를 업데이트, UserUpdateRequestDto에서 받아온 정보로 User 엔터티의 필드를 업데이트하는 역할 수행
        // update 성공시에 id 리턴
    }
}
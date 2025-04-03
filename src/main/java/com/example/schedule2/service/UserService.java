package com.example.schedule2.service;

import com.example.schedule2.dto.LoginResponseDto;
import com.example.schedule2.dto.UserResponseDto;
import com.example.schedule2.entitiy.User;
import com.example.schedule2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public LoginResponseDto login(String userName, String password) {
        // 입력받은 userName, password와 일치하는 Database 조회
        User user = userRepository.findIdByUserNameAndPassword(userName, password).orElseThrow();
        //Long index = (Long) User;

        return new LoginResponseDto(user.getId());
    }

    public UserResponseDto findById(Long id) {

        User user = userRepository.findById(id).orElseThrow();

        return new UserResponseDto(user.getId(), user.getName());
    }

}
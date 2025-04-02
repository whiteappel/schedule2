package com.example.schedule2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 필드 전체를 매개변수로 가진 생성자가 있어야 @ModelAttribute가 동작한다.
@Getter
@AllArgsConstructor
public class LoginRequestDto {
    // 아이디 공백 x
    @NotBlank
    private final String userName;
    //  비밀번호 안채우면 안됨
    @NotNull
    private final String password;
}
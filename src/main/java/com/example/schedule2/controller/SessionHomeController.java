package com.example.schedule2.controller;

import com.example.schedule2.common.Const;
import com.example.schedule2.dto.UserResponseDto;
import com.example.schedule2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class SessionHomeController {

    private final UserService userService;

    @GetMapping("/session-home")
    public String home(
            HttpServletRequest request,
            Model model
    ) {

        HttpSession session = request.getSession(false);

        // session이 없으면 로그인 페이지로 이동
        if(session == null) {
            return "session-login";
        }

        // session에 저장된 유저정보 조회
        // 반환타입이 Object여서 Type Casting이 필요하다.
        // 다른 타입으로 변환 필요
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        // Session에 유저 정보가 없으면 login 페이지 이동
        if (loginUser == null) {
            return "session-login";
        }

        // Session이 정상적으로 조회되면 로그인된것으로 간주
        model.addAttribute("loginUser", loginUser);
        // home 화면으로 이동
        return "session-home";

    }
}
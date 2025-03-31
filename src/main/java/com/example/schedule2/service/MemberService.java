package com.example.schedule2.service;

import com.example.schedule2.dto.SignUpResponseDto;
import com.example.schedule2.entitiy.Member;
import com.example.schedule2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//확장될 여지가 있다면 인터페이스로 설계 필요
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String password, String email) {

        Member member = new Member(username, password, email);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember.getId(),savedMember.getUsername(),savedMember.getEmail());
    }
}

package com.example.schedule2.service;

import com.example.schedule2.config.PasswordEncoder;
import com.example.schedule2.dto.MemberResponseDto;
import com.example.schedule2.dto.SignUpResponseDto;
import com.example.schedule2.entitiy.Member;
import com.example.schedule2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

//확장될 여지가 있다면 인터페이스로 설계 필요
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    //유저 생성
    public SignUpResponseDto signUp(String username, String password, String email) {

        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        Member member = new Member(username, password, email);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember.getId(),savedMember.getUserName(),savedMember.getEmail());
    }
    //유저 정보 조회
    public MemberResponseDto findById(Long id) {
        //옵셔널 쓰는이유 null을 안전하게 다루기 위함
        Optional<Member> optionalMember = memberRepository.findById(id);

        //공백확인
        if(optionalMember.isEmpty()){
            //만약 없다면 NOT_FOUND 필요에 따라 바꾸어도 무방 204 같은 코드로 대체가능
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지않는 id :" + id);

        }

        Member findmember = optionalMember.get();
        //유저네임 이메일 출력
        return new MemberResponseDto(findmember.getUserName(),findmember.getEmail());
    }
    //유저 정보 수정 추가적으로 암호된 비밀번호 검증 필요해짐
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);
        //!을 까먹어 30분간 오류 찾음
        //비밀번호가 일치하지않는다면
        if(!findMember.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }
        //같다면 변경
        findMember.updatePassword(passwordEncoder.encode(newPassword));
    }
    //유저 정보 삭제
    public void delete(Long id) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);

        memberRepository.delete(findMember);
    }
}

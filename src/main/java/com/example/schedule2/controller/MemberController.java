package com.example.schedule2.controller;

import com.example.schedule2.dto.MemberResponseDto;
import com.example.schedule2.dto.SignUpRequestDto;
import com.example.schedule2.dto.SignUpResponseDto;
import com.example.schedule2.dto.UpdatePasswordRequestDto;
import com.example.schedule2.entitiy.Member;
import com.example.schedule2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    //회원등록 생성부분 해당됨
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto){

        SignUpResponseDto signUpResponseDto =
                memberService.signUp(
                        requestDto.getUsername(),
                        requestDto.getPassword(),
                        requestDto.getEmail()
                );

        return new ResponseEntity<>(signUpResponseDto,HttpStatus.CREATED);
    }
    //조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id){
        MemberResponseDto memberResponseDto = memberService.findById(id);
        //조회성공시 ok상태로
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }
    //업데이트
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
    ){

        memberService.updatePassword(id,requestDto.getOldPassword(),requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        memberService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

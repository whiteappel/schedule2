package com.example.schedule2.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity //연결안되서 에러 떳었음
//멤버 테이블 과 연계
@Table(name = "member")
@Getter
@NoArgsConstructor//기본생성자 요구 하길래 만들어줌
public class User {
    //식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동증가 설정
    private Long id;
    //이름

    private String name;
    //이메일
    private String email;
    //로그인 id
    @Column(nullable = false,unique = true)
    private String userName;
    //비밀번호
    @Column(nullable = false)
    private String password;

    public User(Long id, String name, String email, String userName, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

}

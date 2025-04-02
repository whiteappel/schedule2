package com.example.schedule2.entitiy;

import lombok.Getter;

@Getter
public class User {
    //식별자
    private Long id;
    //이름
    private String name;
    //이메일
    private String email;
    //로그인 id
    private String username;
    //비밀번호
    private String password;

    public User(Long id, String name, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}

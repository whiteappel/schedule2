package com.example.schedule2.entitiy;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
//엔티티 선언
@Entity
//테이블 이름 선언
@Table(name ="member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
/* Member 가 빨간색일때 id가 필요해서 만들어졌지만 필요없다.
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

 */
    //공백 불가 필수 , 유일 해야해서 유니크 추가로넣엇음
    //처음에 username으로 추가해서 userName으로 통일 시키는중
    //또한 레포지토리와 서비스에 연관 문제있어서 다시 지정했습니다
    @Column(name = "userName", nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String email;

    //엔티티가붙어있는 클래스는 기본 생성자가 필요함
    public Member() {
    }

    //alt + insert로 생성하기
    public Member(String userName, String password,String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}

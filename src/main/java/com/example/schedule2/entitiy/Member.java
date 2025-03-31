package com.example.schedule2.entitiy;

import jakarta.persistence.*;

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
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;
}

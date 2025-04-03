package com.example.schedule2.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//엔티티 취금
@Entity
//테이블 생성
@Table(name = "board")
//수정일 생성일을 작성위한 extends
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //제목 공백 허용안함
    @Column(nullable = false)
    private String title;
    //긴 문장 가능하게함
    @Column(columnDefinition = "longtext")
    private String contents;

    //연관 관계 설정
    @ManyToOne
    @JoinColumn(name= "member_id")
    private Member member;
    //id는 자동생성에 member은 참조값이기 때문에 제목과 내용만
    public Board(String contents, String title) {
        this.contents = contents;
        this.title = title;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}

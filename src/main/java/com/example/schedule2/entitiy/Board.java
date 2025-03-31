package com.example.schedule2.entitiy;

import jakarta.persistence.*;

//엔티티 취금
@Entity
//테이블 생성
@Table(name = "board")
//수정일 생성일을 작성위한 extends
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
}

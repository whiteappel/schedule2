package com.example.schedule2.repository;

import com.example.schedule2.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//반환목적이기때문에 인터페이스
public interface MemberRepository extends JpaRepository<Member, Long> {

}

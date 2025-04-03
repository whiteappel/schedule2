package com.example.schedule2.repository;

import com.example.schedule2.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

//반환목적이기때문에 인터페이스
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByUserName(String userName);

   default Member findMemberByUsernameOrElseThrow(String userName){
        return findMemberByUserName(userName)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Does not exist username = " + userName)
                );
   }



    default Member findByIdOrElseThrow(Long id){
        return findById(id).
                orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "존재하지않은 id:"+id
                        )
                );
    }
}

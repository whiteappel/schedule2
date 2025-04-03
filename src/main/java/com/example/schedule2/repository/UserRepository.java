package com.example.schedule2.repository;

import com.example.schedule2.dto.UserResponseDto;
import com.example.schedule2.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*
    //이건 유저를 미리 만든상태다
    private static final User USER1 = new User(1L, "wonuk", "100@naver.com", "wonuk", "1234");
    private static final User USER2 = new User(2L, "wonuk2", "200@naver.com", "wonuk2", "2345");
    private static final List<User> USERS = Arrays.asList(USER1, USER2);

    public Long findIdByUserNameAndPassword(String userName, String password) {
        return USERS.stream()
                .filter(user -> user.getUsername().equals(userName) && user.getPassword().equals(password))
                .map(User::getId)
                .findFirst()
                .orElse(null);
    }

    public UserResponseDto findById(Long id) {
        return USERS.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .map(user -> new UserResponseDto(user.getId(), user.getName()))
                .findFirst()
                .orElse(null);
    }

     */

    Optional<User> findIdByUserNameAndPassword(String userName, String password);
}
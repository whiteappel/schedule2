package com.example.schedule2.repository;

import com.example.schedule2.entitiy.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}

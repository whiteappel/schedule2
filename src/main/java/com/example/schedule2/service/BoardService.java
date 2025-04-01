package com.example.schedule2.service;

import com.example.schedule2.dto.BoardResponseDto;
import com.example.schedule2.dto.BoardWithEmailResponseDto;
import com.example.schedule2.entitiy.Board;
import com.example.schedule2.entitiy.Member;
import com.example.schedule2.repository.BoardRepository;
import com.example.schedule2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public BoardResponseDto save(String title, String contents, String username) {

        Member findMember =memberRepository.findMemberByUsernameOrElseThrow(username);

        Board board = new Board(title, contents);
        board.setMember(findMember);

        Board savedBoard = boardRepository.save(board);

        return new BoardResponseDto(savedBoard.getId(),savedBoard.getTitle(),savedBoard.getContents());
    }

    public List<BoardResponseDto> findAll() {

        //리스트형 보드이기때문에

        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::toDto)
                .toList();
    }

    public BoardWithEmailResponseDto findById(Long id) {

        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        Member writer = findBoard.getMember();

        return new BoardWithEmailResponseDto(findBoard.getTitle(), findBoard.getContents(), writer.getEmail());
    }

    public void delete(Long id) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        boardRepository.delete(findBoard);
    }
}

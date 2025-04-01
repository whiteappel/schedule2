package com.example.schedule2.controller;

import com.example.schedule2.dto.BoardResponseDto;
import com.example.schedule2.dto.BoardWithEmailResponseDto;
import com.example.schedule2.dto.CreateBoardRequestDto;
import com.example.schedule2.entitiy.Board;
import com.example.schedule2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    //일정 생성
    @PostMapping
    public ResponseEntity<BoardResponseDto> save(@RequestBody CreateBoardRequestDto requestDto){

        BoardResponseDto boardResponseDto = boardService.save(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getUsername()
        );

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    //일정 전체 조회
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll(){

        List<BoardResponseDto> boardResponseDtoList = boardService.findAll();

        return new ResponseEntity<>(boardResponseDtoList,HttpStatus.OK);
    }
    //일정 일부 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardWithEmailResponseDto> findById(@PathVariable Long id){

        BoardWithEmailResponseDto boardWithEmailResponseDto = boardService.findById(id);

        return new ResponseEntity<>(boardWithEmailResponseDto,HttpStatus.OK);
    }
    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boardService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.example.schedule2.controller;

import com.example.schedule2.dto.BoardResponseDto;
import com.example.schedule2.dto.CreateBoardRequestDto;
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
    //일정 조회
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll(){

        List<BoardResponseDto> boardResponseDtoList = boardService.findAll();

        return new ResponseEntity<>(boardResponseDtoList,HttpStatus.OK);
    }
}

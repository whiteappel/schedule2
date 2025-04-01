package com.example.schedule2.dto;

public class BoardWithEmailResponseDto {

    private final String title;

    private final String contents;

    private final String email;

    public BoardWithEmailResponseDto(String title, String contents, String email) {
        this.title = title;
        this.contents = contents;
        this.email = email;
    }
}

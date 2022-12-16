package com.project.simpleboard.dto;

import com.project.simpleboard.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private String createdAt;
    private String modifiedAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.username = board.getUsername();
        this.createdAt = String.valueOf(board.getCreatedAt());
        this.modifiedAt = String.valueOf(board.getModifiedAt());
    }

}

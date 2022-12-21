package com.project.simpleboard.domain;

import com.project.simpleboard.dto.BoardRequestDto;
import com.project.simpleboard.dto.BoardResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
public class Board extends TimeStamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private final List<Comment> commentList = new ArrayList<>();

    public Board(BoardRequestDto boardRequestDto, String username, Long userId) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.username = username;
        this.userId = userId;
    }

    public BoardResponseDto convertToResponseDto() {
        return new BoardResponseDto(id, username, title, content, getCreatedAt().toString(), getModifiedAt().toString(), commentList.stream().map(Comment::convertToResponseDto).collect(Collectors.toList()));
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }

}

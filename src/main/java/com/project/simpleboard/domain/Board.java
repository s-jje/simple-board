package com.project.simpleboard.domain;

import com.project.simpleboard.dto.BoardRequestDto;
import com.project.simpleboard.dto.BoardResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
    private String title;

//    @Column(nullable = false)
    private String author;

//    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Board(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.author = boardRequestDto.getAuthor();
        this.password = boardRequestDto.getPassword();
        this.content = boardRequestDto.getContent();
    }

    public BoardResponseDto toResponseDto() {
        return new BoardResponseDto(this.getId().longValue(), this.getTitle(), this.getAuthor(), this.getPassword(), this.getContent(), this.getCreatedAt().toString(), this.getModifiedAt().toString());
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.author = boardRequestDto.getAuthor();
        this.password = boardRequestDto.getPassword();
        this.content = boardRequestDto.getContent();
    }
}

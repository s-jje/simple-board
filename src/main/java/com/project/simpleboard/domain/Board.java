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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    private Long userId;

    public Board(BoardRequestDto boardRequestDto, String username, Long userId) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.username = username;
        this.userId = userId;
    }

    public BoardResponseDto toResponseDto() {
        return new BoardResponseDto(this.getId(), this.getUsername(), this.getTitle(), this.getContent(), this.getCreatedAt().toString(), this.getModifiedAt().toString());
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
    }

}

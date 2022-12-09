package com.project.simpleboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;

    private String title;

    private String author;

    private String password;

    private String content;

    private String createdAt;

    private String modifiedAt;
}

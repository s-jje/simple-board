package com.project.simpleboard.dto;

import lombok.Getter;

@Getter
public class BoardDto {

    private Long id;

    private String title;

    private String author;

    private String password;

    private String content;

    private String createdAt;

    private String modifiedAt;
}

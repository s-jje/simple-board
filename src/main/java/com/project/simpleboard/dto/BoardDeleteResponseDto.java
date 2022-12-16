package com.project.simpleboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDeleteResponseDto {

    private String msg;
    private int statusCode;

}

package com.project.simpleboard.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ExceptionResponse {

    private final String msg;
    private final int statusCode;

}

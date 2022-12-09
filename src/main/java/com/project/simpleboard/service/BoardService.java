package com.project.simpleboard.service;

import com.project.simpleboard.dto.BoardRequestDto;
import com.project.simpleboard.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {

    BoardResponseDto register(BoardRequestDto boardRequestDto);

    BoardResponseDto findBoard(Long id);

    List<BoardResponseDto> findBoards();

    BoardResponseDto update(Long id, String password, BoardRequestDto boardRequestDto);

    Long delete(Long id, String password);
}

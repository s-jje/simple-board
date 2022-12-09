package com.project.simpleboard.service;

import com.project.simpleboard.domain.Board;
import com.project.simpleboard.dto.BoardRequestDto;
import com.project.simpleboard.dto.BoardResponseDto;
import com.project.simpleboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto register(BoardRequestDto boardRequestDto) {
        Board board = new Board(boardRequestDto);
        boardRepository.save(board);
        return board.toResponseDto();
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findBoard(Long id) {
        return boardRepository.findBoardById(id).orElseThrow(() -> new NoSuchElementException("Not found.")).toResponseDto();
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(Board::toResponseDto).collect(Collectors.toList());
    }

    @Transactional
    public BoardResponseDto update(Long id, String password, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findBoardById(id).orElseThrow(() -> new NoSuchElementException("Not found."));
        if (password.equals(board.getPassword())) {
            board.update(boardRequestDto);
            return board.toResponseDto();
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Transactional
    public Long delete(Long id, String password) {
        Board board = boardRepository.findBoardById(id).orElseThrow(() -> new NoSuchElementException("Not found."));
        if (password.equals(board.getPassword())) {
            boardRepository.deleteById(id);
            return id;
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}

package com.project.simpleboard.service;

import com.project.simpleboard.domain.Board;
import com.project.simpleboard.dto.BoardDto;
import com.project.simpleboard.repository.DbBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final DbBoardRepository boardRepository;

    @Transactional
    public Board register(BoardDto boardDto) {
        Board board = new Board(boardDto);
        boardRepository.save(board);
        return board;
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        Optional<Board> board = boardRepository.findBoardById(id);
        return board.get();
    }

    @Transactional(readOnly = true)
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public Board update(Long id, BoardDto boardDto) {
        Optional<Board> board = boardRepository.findBoardById(id);
        board.ifPresent(board1 -> {
            boardRepository.save()
        });
    }

    @Transactional
    public Long delete(Long id) {
        boardRepository.deleteById(id);
        return id;
    }
}

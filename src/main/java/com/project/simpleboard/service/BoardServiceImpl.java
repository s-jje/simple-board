package com.project.simpleboard.service;

import com.project.simpleboard.domain.Board;
import com.project.simpleboard.domain.User;
import com.project.simpleboard.dto.BoardDeleteResponseDto;
import com.project.simpleboard.dto.BoardRequestDto;
import com.project.simpleboard.dto.BoardResponseDto;
import com.project.simpleboard.repository.BoardRepository;
import com.project.simpleboard.repository.UserRepository;
import com.project.simpleboard.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public BoardResponseDto register(BoardRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
            Board board = boardRepository.saveAndFlush(new Board(requestDto, user.getUsername(), user.getId()));

            return new BoardResponseDto(board);
        } else {
            throw new AuthenticationCredentialsNotFoundException("Null token");
        }
    }

    @Override
    public BoardResponseDto getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 게시글은 존재하지 않습니다.")).toResponseDto();
    }

    @Override
    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(Board::toResponseDto).collect(Collectors.toList());
    }

    @Transactional
    public BoardResponseDto update(Long id, BoardRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new UsernameNotFoundException("사용자가 존재하지 않습니다."));
            Board board = boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 게시글은 존재하지 않습니다."));

            if (user.getId().longValue() == board.getUserId().longValue()) {
                board.update(requestDto);
                return board.toResponseDto();
            } else {
                throw new BadCredentialsException("작성자가 아닙니다.");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("Null token");
        }
    }

    @Transactional
    public BoardDeleteResponseDto delete(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new UsernameNotFoundException("사용자가 존재하지 않습니다."));
            Board board = boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 게시글은 존재하지 않습니다."));

            if (user.getId().longValue() == board.getUserId().longValue()) {
                boardRepository.deleteById(id);
                return new BoardDeleteResponseDto("게시글 삭제 성공", HttpStatus.OK.value());
            } else {
                throw new BadCredentialsException("작성자가 아닙니다.");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("Null token");
        }
    }

}

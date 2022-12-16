package com.project.simpleboard.controller;

import com.project.simpleboard.dto.BoardRequestDto;
import com.project.simpleboard.dto.BoardResponseDto;
import com.project.simpleboard.dto.LoginRequestDto;
import com.project.simpleboard.dto.SignUpRequestDto;
import com.project.simpleboard.service.BoardService;
import com.project.simpleboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final UserService userService;
    private final BoardService boardService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index", "boards", boardService.findBoards());
    }

    @GetMapping("/boards")
    public ModelAndView registerPage() {
        return new ModelAndView("board");
    }

    @PostMapping("/boards")
    public BoardResponseDto register(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.register(new BoardRequestDto(boardRequestDto.getTitle(), boardRequestDto.getAuthor(), boardRequestDto.getPassword(), boardRequestDto.getContent()));
    }

    @GetMapping("/boards/{id}")
    public ModelAndView showBoard(@PathVariable("id") Long id) {
        return new ModelAndView("content", "board", boardService.findBoard(id));
    }

    @PutMapping("/boards/{id}")
    public BoardResponseDto updateBoard(@PathVariable("id") Long id, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.update(id, boardRequestDto.getPassword(), boardRequestDto);
    }

    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable("id") Long id, @RequestParam("password") String password) {
        boardService.delete(id, password);
    }

    @PostMapping("/auth/signup")
    public String signUp(SignUpRequestDto signupRequestDto) {
        userService.signUp(signupRequestDto);
        return "redirect:/api/user/login";
    }

    @PostMapping("/auth/login")
    public String login(LoginRequestDto loginRequestDto) {
        userService.login(loginRequestDto);
        return "redirect:/api/boards";
    }
}

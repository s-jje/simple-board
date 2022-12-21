package com.project.simpleboard.controller;

import com.project.simpleboard.domain.User;
import com.project.simpleboard.dto.LoginRequestDto;
import com.project.simpleboard.dto.SignUpRequestDto;
import com.project.simpleboard.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserApiController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userServiceImpl.getUsers();
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody @Valid SignUpRequestDto signupRequestDto) {
        userServiceImpl.signUp(signupRequestDto);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userServiceImpl.login(loginRequestDto, response);
        return "로그인 성공";
    }

}

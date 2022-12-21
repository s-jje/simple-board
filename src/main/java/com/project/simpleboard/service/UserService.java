package com.project.simpleboard.service;

import com.project.simpleboard.domain.User;
import com.project.simpleboard.dto.LoginRequestDto;
import com.project.simpleboard.dto.SignUpRequestDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    List<User> getUsers();

    void signUp(SignUpRequestDto signupRequestDto);

    void login(LoginRequestDto loginRequestDto, HttpServletResponse response);

}

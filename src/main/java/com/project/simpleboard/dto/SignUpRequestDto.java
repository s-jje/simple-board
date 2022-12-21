package com.project.simpleboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "아이디는 최소 4자 이상, 10자 이하의 알파벳 소문자(a~z), 숫자(0~9)를 입력하여야 합니다.")
    private String username;

    @Pattern(regexp = "^[a-zA-z0-9]{8,15}$", message = "비밀번호는 최소 8자 이상, 15자 이하의 알파벳 대소문자(a~z, A~Z), 숫자(0~9)를 입력하여야 합니다.")
    private String password;

    private boolean admin = false;

    private String adminToken = "";

    public void adminSignUp(String adminToken) {
        this.admin = true;
        this.adminToken = adminToken;
    }

}

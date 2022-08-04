package com.spring.springdeep01.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class SignupRequestDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z[0-9]]{5,13}$", message = "아이디는 4~12자 영문 대소문자")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9]){5,32}$", message = "비밀번호는 4~32자 영문 대 소문자")
    private String password;

    private String passwordConfirm;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
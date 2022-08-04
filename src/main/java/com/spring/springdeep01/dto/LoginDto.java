package com.spring.springdeep01.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor
@Getter
public class LoginDto {
    private String username;
    private String password;

    public LoginDto(String a,String b){
        this.username = a;
        this.password = b;
    }
}
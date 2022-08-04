package com.spring.springdeep01.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemoRequestDto {
    private String username;
    private String title;
    private String content;
    private String memoPassword;
}

package com.spring.springdeep01.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Getter
public class CommentDto {
    private String username;
    private String content;
    private Long memoId;
}

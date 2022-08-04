package com.spring.springdeep01.controller;

import com.spring.springdeep01.dto.LoginDto;
import com.spring.springdeep01.dto.SignupRequestDto;
import com.spring.springdeep01.model.user.User;
import com.spring.springdeep01.repository.UserRepository;
import com.spring.springdeep01.security.provider.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;
import com.spring.springdeep01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 회원 로그인 페이지
    @PostMapping("/api/member/login")
    public String login(@RequestBody LoginDto loginDto) {
       User user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(
                ()->new RuntimeException("가입되지 않은 아이디 입니다.")
                );
       if(user.getPassword().equals(loginDto.getPassword())){
           throw new RuntimeException("비밀번호가 일치 하지 않습니다.");
       }
        return jwtTokenProvider.createToken(loginDto.getUsername());
    }


    // 회원 가입 요청 처리
    @PostMapping("/api/member/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/api/member/login";
    }


}
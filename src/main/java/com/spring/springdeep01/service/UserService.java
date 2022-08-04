package com.spring.springdeep01.service;

import com.spring.springdeep01.dto.LoginDto;
import com.spring.springdeep01.dto.SignupRequestDto;
import com.spring.springdeep01.model.user.User;
import com.spring.springdeep01.model.user.UserRoleEnum;
import com.spring.springdeep01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);
    }



    public LoginDto login(LoginDto loginDto){
         User user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(
                 ()-> new IllegalArgumentException("아이디가 맞지 않습니다.")
         );
         //// 이거 비밀번호랑 같이 검증 어떻게 하지..??
        if(!passwordEncoder.matches(loginDto.getPassword(),user.getPassword()))
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        //여기에 User 안에다가 만료된 리프레시 토큰 바꾸는 함수 작성후
        //안에 파라미터에다가 jwttokenprovider.createRefreshToken() 넣는다.

          return new LoginDto(loginDto.getUsername(), loginDto.getPassword());
    }

}
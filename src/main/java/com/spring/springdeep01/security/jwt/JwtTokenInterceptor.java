package com.spring.springdeep01.security.jwt;

import com.spring.springdeep01.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String accessToken = request.getHeader("Authorization");

        if (accessToken != null && jwtTokenProvider.validateToken(accessToken)) {
            return true;
        }

        response.setStatus(401);
        response.setHeader("ACCESS_TOKEN", accessToken);
        response.setHeader("msg", "Token이 유효하지 않습니다.");
        response.sendError(401,"Token이 유효하지 않습니다.");
        return false;
    }
}
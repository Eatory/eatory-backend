package com.eatory.mvc.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.eatory.mvc.jwt.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "Authorization"; // 일반적인 헤더 이름

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // Preflight 요청은 통과
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // Authorization 헤더에서 토큰 추출
        String token = request.getHeader(HEADER_AUTH);

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // "Bearer " 제거
            try {
                jwtUtil.validate(token); // 토큰 검증
                return true; // 검증 성공 시 요청 처리 허용
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 상태 코드 설정
                response.getWriter().write("Unauthorized: Invalid token");
                return false; // 요청 차단
            }
        }

        // 토큰이 없거나 유효하지 않은 경우
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 상태 코드 설정
        response.getWriter().write("Unauthorized: Missing or invalid token");
        return false;
    }
}

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
        
        if(token == null || !token.startsWith("Bearer")) {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	response.getWriter().write("Unauthorized: Missing or invalid token");
        	return false;
        }
        
        //"Bearer" 제거
        token = token.substring(7);
        
        try {
        	//토큰 검증 
        	String email = jwtUtil.extractEmail(token); //이메일 추출
        	if(!jwtUtil.validateToken(token, email)) {
        		throw new RuntimeException("Token validation failed");
        	}
        	
        	//필요한 데이터를 요청 속성에 저장 
        	request.setAttribute("email", email);
        	
        	return true;
        } catch (Exception e) {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	response.getWriter().write("Unauthorized" + e.getMessage());
        	return false;
        	
        }
      
        
    }
}

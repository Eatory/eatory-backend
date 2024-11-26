package com.eatory.mvc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eatory.mvc.jwt.JwtUtil;
import com.eatory.mvc.model.dao.UserDao;
import com.eatory.mvc.model.dto.GoogleAccessTokenRequest;
import com.eatory.mvc.model.dto.GoogleProperties;
import com.eatory.mvc.model.dto.SocialLogin;
import com.eatory.mvc.model.dto.SocialLoginRequest;
import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.dto.UserProfile;
import com.eatory.mvc.model.service.GoogleClientService;
import com.eatory.mvc.model.service.SocialLoginService;
import com.eatory.mvc.model.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
@Tag(name = "Social Login API", description = "social login API")
public class SocialLoginController {
	private final GoogleClientService googleService;
	private final GoogleProperties googleProperties;
	private final SocialLoginService socialLoginService;
	private final UserService userService;


	public SocialLoginController(GoogleClientService googleService, GoogleProperties googleProperties,
			SocialLoginService socialLoginService, UserService userService) {
		this.googleService = googleService;
		this.googleProperties = googleProperties;
		this.socialLoginService = socialLoginService;
		this.userService = userService;
	}

	@PostMapping("/google") //로그인 시도 - 회원이 아니면 회원가입 시도 
	public ResponseEntity<Map<String, Object>> loginWithGoogle(@RequestBody SocialLoginRequest socialLoginRequest) {
		try {
            Map<String, Object> response = googleService.socialLogin(socialLoginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "소셜 로그인 중 오류 발생", "error", e.getMessage()));
        }
    }
	
	@GetMapping("/oauth2/code/google")
	public void handleGoogleLogin(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletResponse response) {
	    try {
	        // 1. 구글에서 Authorization Code로 Access Token 요청
	        String accessToken = googleService.getAccessToken(code, googleProperties.getClientId(),
	            googleProperties.getClientSecret(), googleProperties.getRedirectUri());
	        
	        // 2. Access Token으로 사용자 정보 요청
	        Map<String, Object> userInfo = googleService.getGoogleUserInfo(accessToken);

	        // 3. 사용자 데이터 처리
	        SocialLoginRequest socialLoginRequest = new SocialLoginRequest();
	        socialLoginRequest.setEmail((String) userInfo.get("email"));
	        socialLoginRequest.setPlatformUserId((String) userInfo.get("sub"));
	        socialLoginRequest.setPlatformType("Google");
	        socialLoginRequest.setAccessToken(accessToken);

	        Map<String, Object> loginResponse = googleService.socialLogin(socialLoginRequest);

	        // 4. JWT 토큰 세션에 저장
	        String jwtToken = (String) loginResponse.get("access-token");
	        response.addHeader("Authorization", "Bearer " + jwtToken);

	        // 5. `state` 값에 따라 리다이렉션
	        if ("calendar".equals(state)) {
	            // 기존 사용자 → 캘린더로 리다이렉트
	            response.sendRedirect("http://localhost:5173/calendar");
	        } else if ("member-name".equals(state)) {
	            // 신규 사용자 → 이름 입력 화면으로 리다이렉트
	            response.sendRedirect("http://localhost:5173/signup/member-name");
	        } else {
	            // 기본값 (에러 처리 또는 홈으로 이동)
	            response.sendRedirect("http://localhost:5173/");
	        }
	    } catch (Exception e) {
	        try {
	            response.sendRedirect("http://localhost:5173/error?message=" + e.getMessage());
	        } catch (IOException ioException) {
	            ioException.printStackTrace();
	        }
	    }
	}





}

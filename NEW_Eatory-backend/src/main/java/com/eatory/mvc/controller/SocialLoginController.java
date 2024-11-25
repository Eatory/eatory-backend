package com.eatory.mvc.controller;

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
	
	@PostMapping("/oauth2/code/google")
	public ResponseEntity<String> handleGoogleLogin(@RequestParam("code") String code) {
	    try {
	        // 1. Authorization Code로 Access Token 요청
	        String accessToken = googleService.getAccessToken(code, googleProperties.getClientId(),
	            googleProperties.getClientSecret(), googleProperties.getRedirectUri());
	        
	        // 2. Access Token으로 사용자 정보 요청
	        Map<String, Object> userInfo = googleService.getGoogleUserInfo(accessToken);
	        
	     // Step 3: 사용자 데이터 확인 및 처리
	        String email = (String) userInfo.get("email");
	        String platformUserId = (String) userInfo.get("sub");

	        if (email == null || platformUserId == null) {
	            throw new RuntimeException("Google 사용자 정보에서 email 또는 platformUserId를 찾을 수 없습니다.");
	        }

	        // Step 4: SocialLoginRequest 생성 및 서비스 호출
	        SocialLoginRequest socialLoginRequest = new SocialLoginRequest();
	        socialLoginRequest.setEmail(email);
	        socialLoginRequest.setPlatformUserId(platformUserId);
	        socialLoginRequest.setPlatformType("Google");
	        socialLoginRequest.setAccessToken(accessToken);

	        // 서비스 계층에서 처리 (회원가입 또는 로그인)
	        Map<String, Object> response = googleService.socialLogin(socialLoginRequest);


	        return ResponseEntity.ok("로그인 성공");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + e.getMessage());
	    }
	} 


}

package com.eatory.mvc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	private final JwtUtil jwtUtil;

	public SocialLoginController(GoogleClientService googleService, GoogleProperties googleProperties,
			SocialLoginService socialLoginService, UserService userService, JwtUtil jwtUtil) {
		this.googleService = googleService;
		this.googleProperties = googleProperties;
		this.socialLoginService = socialLoginService;
		this.userService = userService;
		this.jwtUtil = jwtUtil;
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
	
	@GetMapping("/oauth2/code/google") //구글에서 받은 Authoriazation Code를 프론트엔드로 리디렉션 
	public ResponseEntity<?> handleGoogleLogin(@RequestParam("code") String code) {
	    try {
	        // 1. 구글에서 Authorization Code로 Access Token 요청
	        String accessToken = googleService.getAccessToken(code, googleProperties.getClientId(),
	            googleProperties.getClientSecret(), googleProperties.getRedirectUri());
	        
	       // 2. Authorization Code를 프론트엔드로 리디렉션
	        return ResponseEntity.ok(Map.of("access-token", accessToken));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("accessToken 반환에 실패!");
	    }
	}
	
	@GetMapping("/user-info")
	public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        try {
        	 // Google Access Token으로 사용자 정보 요청
            String accessToken = token.substring(7); // "Bearer " 제거
            RestTemplate restTemplate = new RestTemplate();

            // Google API에 요청
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v1/userinfo?alt=json",
                HttpMethod.GET,
                entity,
                Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                Map userInfo = response.getBody();
                return ResponseEntity.ok(userInfo); // 사용자 정보를 반환
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to fetch user info from Google");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired Google Access Token");
        }
    }





}

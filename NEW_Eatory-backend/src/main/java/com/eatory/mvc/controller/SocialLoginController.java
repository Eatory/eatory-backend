package com.eatory.mvc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eatory.mvc.model.dto.GoogleAccessTokenRequest;
import com.eatory.mvc.model.dto.GoogleProperties;
import com.eatory.mvc.model.dto.SocialLogin;
import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.service.GoogleClientService;
import com.eatory.mvc.model.service.SocialLoginService;
import com.eatory.mvc.model.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/social")
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

	@PostMapping("/google/login") //로그인 시도 - 회원이 아니면 회원가입 시도 
	public ResponseEntity<String> loginWithGoogle(@RequestBody GoogleAccessTokenRequest request) {
		try {
			// 디버깅: 요청 본문 출력
			System.out.println("Received Request Body: " + request);
			// 필수 필드 확인
			if (request.getCode() == null || request.getCode().isEmpty()) {
				throw new IllegalArgumentException("Authorization code is missing");
			}
			System.out.println("Google Properties: " + googleProperties);
			
			//1. 구글에서 AccessToken 가져오기 
			String accessToken = googleService.getAccessToken(request.getCode(), googleProperties.getClientId(),
					googleProperties.getClientSecret(), googleProperties.getRedirectUri());
			
		
			// 2. 사용자 정보 요청
	        Map<String, Object> googleUserInfo = googleService.getGoogleUserInfo(accessToken);

			
			//3. 기존 소셜 로그인 정보 확인
	        String email = (String) googleUserInfo.get("email");
	        String platformUserId = (String) googleUserInfo.get("sub");
	        
	        if (email == null || platformUserId == null) {
	            throw new RuntimeException("구글 사용자 정보에서 email 또는 platformUserId를 찾을 수 없습니다.");
	        }
	        
			User existingUser = userService.findUserByEmail(email);
			System.out.println("기존 회원:" + existingUser + "email:" + email);
	        Long userId;
	        
	        if (existingUser == null) {
	            // 3-1. User 테이블에 사용자 등록
	        	User newUser = new User();
	            newUser.setEmail(email);
	            newUser.setUsername("hi"); // 사용자 이름은 나중에 입력받을 수 있음
	            newUser.setPassword(null); // 소셜 로그인은 비밀번호 없음
	            
	            userService.signup(newUser);
	            
	            userId = userService.findUserId(email);
	            System.out.println("userId: " + userId);
		        
	        } else {
	            userId = existingUser.getUserId();
	        }
			
			//4. 신규 소셜 로그인 정보 등록
			SocialLogin socialLogin = new SocialLogin();
			socialLogin.setUserId(userId);
			socialLogin.setEmail(email);
			socialLogin.setPlatformUserId(platformUserId);
			socialLogin.setPlatformType("Google");
			socialLogin.setAccessToken(accessToken);
			
			socialLoginService.registerSocialLogin(socialLogin);	
			return ResponseEntity.ok("구글 소셜 로그인 등록이 완료되었습니다.");

		} catch (Exception e) {
			return ResponseEntity.status(500).body("구글 소셜 로그인 처리 중 오류 발생: " + e.getMessage());
		}
	}

}

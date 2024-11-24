package com.eatory.mvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eatory.mvc.model.dto.GoogleAccessTokenRequest;
import com.eatory.mvc.model.dto.GoogleProperties;
import com.eatory.mvc.model.service.GoogleClientService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/google")
@CrossOrigin("*")
@Tag(name="Google Social Login API", description="google social login API")
public class GoogleLoginController {
	private final GoogleClientService googleService;
	private final GoogleProperties googleProperties;
	
	public GoogleLoginController(GoogleClientService googleService, GoogleProperties googleProperties) {
		this.googleService = googleService;
		this.googleProperties = googleProperties;
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginWithGoogle(@RequestBody GoogleAccessTokenRequest request) {
		 try {
			 	// 디버깅: 요청 본문 출력
		        System.out.println("Received Request Body: " + request);
		        
		        // 필수 필드 확인
		        if (request.getCode() == null || request.getCode().isEmpty()) {
		            throw new IllegalArgumentException("Authorization code is missing");
		        }

		        String accessToken = googleService.getAccessToken(
		                request.getCode(),
		                googleProperties.getClientId(),
		                googleProperties.getClientSecret(),
		                googleProperties.getRedirectUri()
		            );

	            return ResponseEntity.ok(accessToken);

	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error during Google login: " + e.getMessage());
	        }
	}

}


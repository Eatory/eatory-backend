package com.eatory.mvc.model.service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.eatory.mvc.model.dto.GoogleAccessTokenRequest;
import com.eatory.mvc.model.dto.GoogleAccessTokenResponse;

@Service
public class GoogleClientServiceImpl implements GoogleClientService{
	
	private static final Logger logger = LoggerFactory.getLogger(GoogleClientServiceImpl.class);
	private final RestTemplate restTemplate;
	private final String accessTokenUrl = "https://oauth2.googleapis.com/token";
	
	public GoogleClientServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public String getAccessToken(String code, String clientId, String clientSecret, String redirectUri) {
		try {
			//요청 본문 생성 확인 
			String decodedCode = URLDecoder.decode(code, StandardCharsets.UTF_8);
			logger.info("Decoded Authorization Code: {}", decodedCode);
			
			//Http 요청 헤더 로그 
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			//요청 본문 생성 확인 
			GoogleAccessTokenRequest requestBody = new GoogleAccessTokenRequest(
					decodedCode, clientId, clientSecret, redirectUri, "authorization_code"
			);
			logger.info("Request Body: {}", requestBody);
			
			//요청 객체 확인 
			HttpEntity<GoogleAccessTokenRequest> request = new HttpEntity<>(requestBody, headers);
			
			//Google로 요청 보내기 
			ResponseEntity<GoogleAccessTokenResponse> response = restTemplate.exchange(
					accessTokenUrl, HttpMethod.POST, request, GoogleAccessTokenResponse.class);
			
			 // Google 응답 확인
	        logger.info("Response from Google: {}", response.getBody());
			
	        // 응답 본문에서 Access Token 추출 
			return Optional.ofNullable(response.getBody())
					.map(GoogleAccessTokenResponse::getAccessToken)
					.orElseThrow(() -> new RuntimeException("Access token not found in response"));

		} catch (HttpClientErrorException e) {
			String responseBody = e.getResponseBodyAsString();
	        if (responseBody.contains("invalid_grant")) {
	            logger.error("Authorization Code가 유효하지 않습니다. 새로 요청해야 합니다.");
	            throw new RuntimeException("Invalid Authorization Code: " + responseBody);
	        }
	        throw e; // 기타 오류 처리
        } catch (Exception e) {
            logger.error("Unexpected error while requesting access token: {}", e.getMessage(), e);
            throw new RuntimeException("An error occurred while requesting access token: " + e.getMessage(), e);
        }
	}

}

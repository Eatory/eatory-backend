package com.eatory.mvc.model.service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.eatory.mvc.jwt.JwtUtil;
import com.eatory.mvc.model.dao.UserDao;
import com.eatory.mvc.model.dto.GoogleAccessTokenRequest;
import com.eatory.mvc.model.dto.GoogleAccessTokenResponse;
import com.eatory.mvc.model.dto.SocialLoginRequest;
import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.dto.UserProfile;

@Service
public class GoogleClientServiceImpl implements GoogleClientService {

	private static final Logger logger = LoggerFactory.getLogger(GoogleClientServiceImpl.class);
	private final RestTemplate restTemplate;
	private final UserDao userDao;
	private final JwtUtil jwtUtil;
	private final String accessTokenUrl = "https://oauth2.googleapis.com/token";
	private final String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";

	public GoogleClientServiceImpl(RestTemplate restTemplate, UserDao userDao, JwtUtil jwtUtil) {
		this.restTemplate = restTemplate;
		this.userDao = userDao;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public String getAccessToken(String code, String clientId, String clientSecret, String redirectUri) {
		try {
			// 요청 본문 생성 확인
			String decodedCode = URLDecoder.decode(code, StandardCharsets.UTF_8);
			logger.info("Decoded Authorization Code: {}", decodedCode);

			// Http 요청 헤더 로그
			HttpHeaders headers = new HttpHeaders(); 
			headers.setContentType(MediaType.APPLICATION_JSON);

			// 요청 본문 생성 확인
			GoogleAccessTokenRequest requestBody = new GoogleAccessTokenRequest(decodedCode, clientId, clientSecret,
					redirectUri, "authorization_code");
			logger.info("Request Body: {}", requestBody);

			// 요청 객체 확인
			HttpEntity<GoogleAccessTokenRequest> request = new HttpEntity<>(requestBody, headers);

			// Google로 요청 보내기
			ResponseEntity<GoogleAccessTokenResponse> response = restTemplate.exchange(accessTokenUrl, HttpMethod.POST,
					request, GoogleAccessTokenResponse.class);

			// Google 응답 확인
			logger.info("Response from Google: {}", response.getBody());

			// 응답 본문에서 Access Token 추출
			return Optional.ofNullable(response.getBody()).map(GoogleAccessTokenResponse::getAccessToken)
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
	
	
	//소셜 로그인 
		@Override
		@Transactional
		public Map<String, Object> socialLogin(SocialLoginRequest socialLoginRequest) {
		    Map<String, Object> response = new HashMap<>();

		    // 소셜 로그인 데이터 가져오기
		    String email = socialLoginRequest.getEmail();
		    String platformUserId = socialLoginRequest.getPlatformUserId();
		    String platformType = socialLoginRequest.getPlatformType();

		    // 사용자 확인
		    User user = userDao.findUserByEmail(email);
		    if (user == null) {
		        // 신규 사용자 등록
		        user = new User();
		        user.setEmail(email);
		        user.setUsername(socialLoginRequest.getPlatformType() + "User"); // 기본 이름 설정
		        user.setPassword(null); // 소셜 로그인은 비밀번호 없음
		        userDao.insertUser(user);

		        // 추가 프로필 정보 저장
		        User userProfile = new User();
		        userProfile.setUserId(user.getUserId());
		        userProfile.setUsername(user.getUsername());
		        userProfile.setEmail(email);
		        userProfile.setPhoneNumber(socialLoginRequest.getPhoneNumber());
		        userProfile.setHeight(socialLoginRequest.getHeight());
		        userProfile.setWeight(socialLoginRequest.getWeight());
		        userProfile.setGender(socialLoginRequest.getGender());
		        userProfile.setBirthDate(socialLoginRequest.getBirthDate());
		        userDao.insertUser(userProfile);
		    }

		    // AccessToken 및 RefreshToken 생성
		    String accessToken = jwtUtil.createAccessToken(email);
		    String refreshToken = jwtUtil.createRefreshToken(email);
		    Date expiresAt = new Date(System.currentTimeMillis() + jwtUtil.getRefreshTokenExpireTime());

		    // Refresh Token 저장
		    userDao.saveRefreshToken(email, refreshToken, expiresAt);

		    // 사용자 프로필 가져오기
		    UserProfile userProfile = userDao.findUserProfile(user.getUserId());
		    if (userProfile == null) {
		        userProfile = new UserProfile(); // 기본 프로필
		        userProfile.setUserId(user.getUserId());
		        userProfile.setUsername(user.getUsername());
		        userProfile.setEmail(user.getEmail());
		        userProfile.setProfileImage("");
		        userProfile.setPostCount(0);
		        userProfile.setFollowerCount(0);
		        userProfile.setFolloweeCount(0);
		        userProfile.setAllergies(List.of());
		        userProfile.setHeight(0); 
		        userProfile.setWeight(0);
		    }

		    // 응답 데이터 구성
		    response.put("message", "Social Login 성공");
		    response.put("access-token", accessToken);
		    response.put("refresh-token", refreshToken);
		    response.put("user",
		            Map.of("userId", user.getUserId(),
		                    "username", user.getUsername(),
		                    "email", user.getEmail(),
		                    "profileImage", userProfile.getProfileImage(),
		                    "postCount", userProfile.getPostCount(),
		                    "followerCount", userProfile.getFollowerCount(),
		                    "followeeCount", userProfile.getFolloweeCount(),
		                    "allergies", userProfile.getAllergies(),
		                    "height", userProfile.getHeight(),
		                    "weight", userProfile.getWeight()));

		    return response;
		}

	//플랫폼 userID 추출
	@Override
	public Map<String, Object> getGoogleUserInfo(String accessToken) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(accessToken);
			
			//요청 생성
			HttpEntity<Void> request = new HttpEntity<>(headers);
			
			//Google UserInfo Api 호출
			ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
	                userInfoUrl, HttpMethod.GET, request,
	                new ParameterizedTypeReference<Map<String, Object>>() {}
	        );
			
			return response.getBody();
		} catch (Exception e) {
			throw new RuntimeException("구글 사용자 정보 요청 중 오류 발생: " + e.getMessage(), e);
		}
	}

}

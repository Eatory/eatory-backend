package com.eatory.mvc.model.service;

import java.util.Map;

public interface GoogleClientService {
	/**
     * Google OAuth2 Access Token을 요청합니다.
     *
     * @param code        클라이언트로부터 전달받은 인가 코드
     * @param clientId    구글 개발자센터에서 발급받은 Client ID
     * @param clientSecret 구글 개발자센터에서 발급받은 Client Secret
     * @param redirectUri 구글 개발자센터에서 등록한 Redirect URI
     * @return Access Token 문자열
     */
    String getAccessToken(String code, String clientId, String clientSecret, String redirectUri);

	
	Map<String, Object> getGoogleUserInfo(String accessToken);
}

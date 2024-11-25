package com.eatory.mvc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleAccessTokenResponse {
	@JsonProperty("access_token")
    private String accessToken; // 구글 액세스 토큰 

    @JsonProperty("expires_in")
    private String expiresIn; // 토큰의 만료 시간 

    @JsonProperty("scope")
    private String scope; // 조회하고자 하는 사용자 정보 

    @JsonProperty("token_type")
    private String tokenType; // 토큰 유형 (Bearer로 고정)

    @JsonProperty("id_token")
    private String idToken; // 구글 ID 토큰

	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getIdToken() {
		return idToken;
	}
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	@Override
	public String toString() {
		return "GoogleAccessTokenResponse [accessToken=" + accessToken + ", expiresIn=" + expiresIn + ", scope=" + scope
				+ ", tokenType=" + tokenType + ", idToken=" + idToken + "]";
	}
	
	
	
	

}

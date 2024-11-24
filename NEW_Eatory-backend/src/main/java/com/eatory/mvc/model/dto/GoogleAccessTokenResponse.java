package com.eatory.mvc.model.dto;

public class GoogleAccessTokenResponse {
	private String accessToken; //구글 액세스 토큰 
	private String expiresIn; //구글 액세스 토큰의 만료 시간 (초 단위) 
	private String scope; //조회하고자 하는 사용자의 정보 
	private String tokenType; //토큰 유형. Bearer로 고정 
	private String idToken; //구글 리프레시 토큰 
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
	
	
	
	

}

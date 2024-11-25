package com.eatory.mvc.model.dto;

public class SocialLogin {
	private Long socialId; // 소셜 로그인 ID
    private Long userId; // 연결된 사용자 ID
    private String platformUserId; // 플랫폼 사용자 ID
    private String platformType; // 플랫폼 유형 (e.g., GOOGLE)
    private String accessToken; // 액세스 토큰
    private String email; // 사용자 이메일
    private String username;
    private String password;
    
	public Long getSocialId() {
		return socialId;
	}
	public void setSocialId(Long socialId) {
		this.socialId = socialId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPlatformUserId() {
		return platformUserId;
	}
	public void setPlatformUserId(String platformUserId) {
		this.platformUserId = platformUserId;
	}
	public String getPlatformType() {
		return platformType;
	}
	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    
    
}

package com.eatory.mvc.model.dto;

public class GoogleAccessTokenRequest {
	private String code;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String grantType;
	
	public GoogleAccessTokenRequest() {}
	
	public GoogleAccessTokenRequest(String code, String clientId, String clientSecret, String redirectUri,
			String grantType) {
		super();
		this.code = code; //클라이언트로부터 전달받은 인가 코드 
		this.clientId = clientId; // 구글 개발자센터에서 발급 받은 Client ID 
		this.clientSecret = clientSecret; //구글 개발자센터에서 발급 받은 Client Secret 
		this.redirectUri = redirectUri; //구글 개발자센터에서 등록한 redirect_uri 
		this.grantType = grantType; //'authorization_code'로 고정(인가코드를 통한 로그인 방식) 
	}

	// Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

	@Override
	public String toString() {
		return "GoogleAccessTokenRequest [code=" + code + ", clientId=" + clientId + ", clientSecret=" + clientSecret
				+ ", redirectUri=" + redirectUri + ", grantType=" + grantType + "]";
	}
	
    
	

}

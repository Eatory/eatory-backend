package com.eatory.mvc.model.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "google")
public class GoogleProperties {
	private static final Logger logger = LoggerFactory.getLogger(GoogleProperties.class);

	
	private String clientId;
	private String clientSecret;
	private String redirectUri;
	
	
	@PostConstruct
    public void logProperties() {
        logger.info("Google Properties - clientId: {}, clientSecret: {}, redirectUri: {}",
                    clientId, clientSecret, redirectUri);
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
	
	

}

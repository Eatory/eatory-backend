package com.eatory.mvc.model.service;

import com.eatory.mvc.model.dto.TokenResponseDto;

public interface AuthService {
	TokenResponseDto login(String memberId);
	
	boolean validateAccessToken(String token);
	
	String extractMemberIdFromToken(String token);
	

}

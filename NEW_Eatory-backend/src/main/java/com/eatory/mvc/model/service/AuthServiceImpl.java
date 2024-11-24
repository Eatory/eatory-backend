package com.eatory.mvc.model.service;

import org.springframework.stereotype.Service;

import com.eatory.mvc.jwt.JwtUtil;
import com.eatory.mvc.model.dto.TokenResponseDto;

@Service
public class AuthServiceImpl implements AuthService{
	private final JwtUtil jwtUtil;
	
	public AuthServiceImpl (JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	public TokenResponseDto login(String memberId) {
		String accessToken = jwtUtil.createAccessToken(memberId);
		String refreshToken = jwtUtil.createRefreshToken(memberId);
		return new TokenResponseDto(accessToken, refreshToken);
	}

	@Override
	public boolean validateAccessToken(String token) {
		return jwtUtil.validateToken(token, jwtUtil.extractEmail(token));
	}

	@Override
	public String extractMemberIdFromToken(String token) {
		return jwtUtil.extractEmail(token);
	}




}

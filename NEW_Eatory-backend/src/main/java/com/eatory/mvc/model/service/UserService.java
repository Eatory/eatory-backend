package com.eatory.mvc.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.eatory.mvc.model.dto.LoginRequest;
import com.eatory.mvc.model.dto.SocialLoginRequest;
import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.dto.UserProfile;


public interface UserService {
	
	//전체 사용자 목록 불러오기 
	public List<User> getUserList();
	
	//사용자 등록하기 
	public boolean signup(User user);
	
	//로그인 하기 
	public User login(String email, String password);
	
	//회원 프로필 정보 가져오기 
	public UserProfile getUserProfile(Long userId);
	
	//로그인 처리 및 토큰 생성  
	public Map<String, Object> loginUser(LoginRequest loginRequest);
	
	//Refresh 토큰 검증 및 새로운 Access Token 발급 
	public String refreshAccessToken(String email, String refreshToken);

	public boolean logoutUser(String email);
	
	public Long findUserId(String email);
	
	public User findUserByEmail(String email);



	

}

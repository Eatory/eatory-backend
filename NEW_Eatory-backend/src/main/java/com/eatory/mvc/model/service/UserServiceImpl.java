package com.eatory.mvc.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eatory.mvc.jwt.JwtUtil;
import com.eatory.mvc.model.dao.UserDao;
import com.eatory.mvc.model.dto.LoginRequest;
import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.dto.UserProfile;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserDao userDao;
	private final JwtUtil jwtUtil;

	public UserServiceImpl(UserDao userDao, JwtUtil jwtUtil) {
		this.userDao = userDao;
		this.jwtUtil = jwtUtil;
	}
	
	
	@Override
	@Transactional
	//전체 사용자 목록 불러오기 
	public List<User> getUserList(){
		return userDao.selectAll();
	}
	
	@Override
	@Transactional
	//사용자 등록하기 (회원가입)
	public boolean signup(User user) {
		try {
			userDao.insertOne(user);
			return true;
		} catch (Exception e) {
			//필요한 경우 예외를 로깅하거나 처리
			return false;
		}
		
	}
	
	@Override
	@Transactional
	//로그인 하기 
	public User login(String email, String password) {
		return userDao.selectOne(email, password);
	}

	@Override
	@Transactional
	//회원 프로필 정보 가져오기 
	public UserProfile getUserProfile(Long userId) {
		return userDao.findeUserProfile(userId);
	}


	@Override
	@Transactional
	//로그인 처리 및 토큰 생성 
	public Map<String, Object> loginUser(LoginRequest loginRequest) {
		Map<String, Object> response = new HashMap<>();
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		//사용자 인증 
		User user = userDao.findUserByEmailAndPassword(email, password);
		if(user != null) {
			//AccessToken 및 Refresh Token 생성 
			String accessToken = jwtUtil.createAccessToken(email);
			String refreshToken = jwtUtil.createRefreshToken(email);
			
			//Refresh Token 저장
			userDao.saveRefreshToken(email, refreshToken);
			
			//응답 데이터 구성 
			response.put("message", "Login 성공");
			response.put("access-token", accessToken);
			response.put("refresh-token", refreshToken);
			response.put("user", Map.of("email", user.getEmail(), "name", user.getUsername()));
			
		} else {
			response.put("message", "Login 실패");
		}
		
		return response;
	}


	@Override
	@Transactional
	//Refresh 토큰 검증 및 새로운 Access Token 발급 
	public String refreshAccessToken(String email, String refreshToken) {
		//Refresh Token 검증 
		String savedToken = userDao.getRefreshTokenByEmail(email);
		
		if(savedToken != null && savedToken.equals(refreshToken) && !jwtUtil.isTokenExpired(refreshToken)){
			//새로운 Access Token 발급 
			return jwtUtil.createAccessToken(email);
		}
		
		return null; //유효하지 않은 Refresh Token
	}


	
	
	

}

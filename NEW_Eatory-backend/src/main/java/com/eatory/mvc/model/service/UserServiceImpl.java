package com.eatory.mvc.model.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eatory.mvc.jwt.JwtUtil;
import com.eatory.mvc.model.dao.AllergyDao;
import com.eatory.mvc.model.dao.UserDao;
import com.eatory.mvc.model.dto.LoginRequest;
import com.eatory.mvc.model.dto.SocialLoginRequest;
import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.dto.UserProfile;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final AllergyDao allergyDao;
	private final JwtUtil jwtUtil;

	public UserServiceImpl(UserDao userDao, AllergyDao allergyDao, JwtUtil jwtUtil) {
		this.userDao = userDao;
		this.allergyDao = allergyDao;
		this.jwtUtil = jwtUtil;
	}

	@Override
	@Transactional
	// 전체 사용자 목록 불러오기
	public List<User> getUserList() {
		return userDao.selectAll();
	}
	
	@Override
	@Transactional
	public Long findUserId(String email) { 
		return userDao.findUserIdByEmail(email);
	}
	

	@Override
	@Transactional
	// 사용자 등록하기 (회원가입)
	public boolean signup(User user) {
		try {
			userDao.insertUser(user);
			System.out.println("Generated User ID: " + user.getUserId());
			return true;
		} catch (Exception e) {
			e.printStackTrace(); 
			return false;
		}

	} 

	@Override
	@Transactional
	// 로그인 하기
	public User login(String email, String password) {
		return userDao.selectUser(email, password);
	}

	@Override
	public UserProfile getUserProfile(Long userId) {
		if (userId == null) {
			throw new IllegalArgumentException("User ID cannot be null");
		}
		return userDao.findUserProfile(userId);
	}

	@Override
	@Transactional
	// 로그인 처리 및 토큰 생성
	public Map<String, Object> loginUser(LoginRequest loginRequest) {
		Map<String, Object> response = new HashMap<>();
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		// 사용자 인증
		User user = userDao.findUserByEmailAndPassword(email, password);
		if (user != null) {
			// AccessToken 및 Refresh Token 생성
			String accessToken = jwtUtil.createAccessToken(email);
			String refreshToken = jwtUtil.createRefreshToken(email);
			Date expiresAt = new Date(System.currentTimeMillis() + jwtUtil.getRefreshTokenExpireTime());

			// Refresh Token 저장
			userDao.saveRefreshToken(email, refreshToken, expiresAt);

			// 사용자 프로필 추가 데이터 조회
			UserProfile userProfile = userDao.findUserProfile(user.getUserId());
			if (userProfile == null) {
				userProfile = new UserProfile(); // null 안전 처리
				userProfile.setUserId(user.getUserId());
				userProfile.setUsername(user.getUsername());
				userProfile.setEmail(user.getEmail());
				userProfile.setProfileImage("");
				userProfile.setPostCount(0);
				userProfile.setFollowerCount(0);
				userProfile.setFolloweeCount(0);
				userProfile.setAllergies(List.of());
				userProfile.setHeight( 0);
				userProfile.setWeight(0);
			}

			// 응답 데이터 구성
			response.put("message", "Login 성공");
			response.put("access-token", accessToken);
			response.put("refresh-token", refreshToken);
			response.put("user",
					Map.of("userId", user.getUserId(), 
							"username", user.getUsername(), 
							"email", user.getEmail(),
							"profileImage", userProfile.getProfileImage(), 
							"postCount", userProfile.getPostCount(),
							"followerCount", userProfile.getFollowerCount(), 
							"followeeCount",userProfile.getFolloweeCount(), 
							"allergies", userProfile.getAllergies(), 
							"height", userProfile.getHeight(), 
							"weight", userProfile.getWeight())); // 사용자 데이터 추가

		} else {
			response.put("message", "Login 실패");
		}

		return response;
	}


	@Override
	@Transactional
	// Refresh 토큰 검증 및 새로운 Access Token 발급
	public String refreshAccessToken(String email, String refreshToken) {
		// Refresh Token 검증
		String savedToken = userDao.getRefreshTokenByEmail(email);

		if (savedToken != null && savedToken.equals(refreshToken) && !jwtUtil.isTokenExpired(refreshToken)) {
			// 새로운 Access Token 발급
			return jwtUtil.createAccessToken(email);
		}

		return null; // 유효하지 않은 Refresh Token
	}

	@Override
	@Transactional
	public boolean logoutUser(String email) {
		try {
			userDao.deleteRefreshTokenByEmail(email);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Override
	@Transactional
	public boolean updateUsername(Long userId, String username) {
		try {
            int rowsAffected = userDao.updateUsername(userId, username);
            return rowsAffected > 0; // 업데이트 성공 여부 확인
        } catch (Exception e) {
            throw new RuntimeException("Username 업데이트 중 오류 발생: " + e.getMessage(), e);
        }
	}

	

}

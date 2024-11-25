package com.eatory.mvc.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.dto.UserProfile;

public interface UserDao {

	public List<User> selectAll();

	public void insertUser(User user);

	User selectUser(@Param("email") String email, @Param("password") String password);
	
	//프로필 정보 가져오기
	public UserProfile findUserProfile(@Param("userId") Long userId);

	public User findUserByEmailAndPassword(String email, String password);

	public String getRefreshTokenByEmail(String email);

	public void saveRefreshToken(String email, String refreshToken, Date expiresAt);

	public void deleteRefreshTokenByEmail(String email);
	
	public Long findUserIdByEmail(@Param("email") String email);

	public User findUserByEmail(String email);
	

}

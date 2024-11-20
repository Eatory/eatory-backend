package com.eatory.mvc.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.dto.UserProfile;

public interface UserDao {

	public List<User> selectAll();

	public void insertOne(User user);

	User selectOne(@Param("email") String email, @Param("password") String password);

	public UserProfile findUserProfile(Long userId);

	public User findUserByEmailAndPassword(String email, String password);

	public String getRefreshTokenByEmail(String email);

	public void saveRefreshToken(String email, String refreshToken, Date expiresAt);

	public void deleteRefreshTokenByEmail(String email);

}

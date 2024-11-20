package com.eatory.mvc.model.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenDao {
	void saveRefreshToken(@Param("email") String email, @Param("refreshToken") String refreshToken, @Param("expiresAt") Date expiresAt);
    String getRefreshTokenByEmail(String email);
    void deleteRefreshTokenByEmail(String email);

}

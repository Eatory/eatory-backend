package com.eatory.mvc.model.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenDao {
	void saveRefreshToken(String email, String refreshToken, String expiresAt);
    String getRefreshTokenByEmail(String email);
    void deleteRefreshTokenByEmail(String email);

}

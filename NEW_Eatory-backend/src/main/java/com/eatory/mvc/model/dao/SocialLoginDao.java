package com.eatory.mvc.model.dao;

import org.apache.ibatis.annotations.Param;

import com.eatory.mvc.model.dto.SocialLogin;

public interface SocialLoginDao {
	
	//소셜 로그인 정보 삽입
	void insertSocialLogin(SocialLogin socialLogin);
	
	// 이메일로 소셜 로그인 정보 조회
    SocialLogin findSocialLoginByEmail(@Param("email") String email);

    // 플랫폼 사용자 ID와 플랫폼 유형으로 조회
    SocialLogin findSocialLoginByPlatformUserIdAndType( 
        @Param("platformUserId") String platformUserId, 
        @Param("platformType") String platformType
    );

    // 소셜 로그인 정보 삭제
    void deleteSocialLoginByUserId(@Param("userId") Long userId);

}

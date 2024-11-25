package com.eatory.mvc.model.service;

import com.eatory.mvc.model.dto.SocialLogin;

public interface SocialLoginService {
	void registerSocialLogin(SocialLogin socialLogin); //소셜 로그인 정보 
	SocialLogin getSocialLoginByEmail(String email); //이메일로 소셜 로그인 정보 조회
	SocialLogin getSocialLoginByPlatformUserIdAndType(String platformUSerid, String platformType);//플랫폼 사용자 ID와 유형으로 조회
	
	
}

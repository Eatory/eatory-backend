package com.eatory.mvc.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eatory.mvc.model.dao.SocialLoginDao;
import com.eatory.mvc.model.dto.SocialLogin;

@Service
public class SocialLoginServiceImpl implements SocialLoginService{
	
	private final SocialLoginDao socialLoginDao;
	
	public SocialLoginServiceImpl(SocialLoginDao socialLoginDao) {
        this.socialLoginDao = socialLoginDao;
    }
	
	@Transactional
	@Override
	public void registerSocialLogin(SocialLogin socialLogin) {
		socialLoginDao.insertSocialLogin(socialLogin);
		
	}

	@Override
	public SocialLogin getSocialLoginByEmail(String email) {
		return socialLoginDao.findSocialLoginByEmail(email);
	}

	@Override
	public SocialLogin getSocialLoginByPlatformUserIdAndType(String platformUSerid, String platformType) {
		return socialLoginDao.findSocialLoginByPlatformUserIdAndType(platformUSerid, platformType);
	}
	
	

}

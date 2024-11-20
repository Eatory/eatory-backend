package com.eatory.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.dto.UserProfile;


public interface UserService {
	
	//전체 사용자 목록 불러오기 
	public List<User> getUserList();
	
	//사용자 등록하기 
	public boolean signup(User user);
	
	//로그인 하기 
	public User login(String email, String password);

	public UserProfile getUserProfile(Long userId);


	

}

package com.eatory.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eatory.model.dto.User;


public interface UserService {
	
	//전체 사용자 목록 불러오기 
	public List<User> getUserList();
	
	//사용자 등록하기 
	public boolean registerUser(User user);
	
	//로그인 하기 
	public User login(Long userId, String password);
	

}

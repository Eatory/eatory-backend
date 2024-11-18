package com.eatory.mvc.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eatory.mvc.model.dao.UserDao;
import com.eatory.mvc.model.dto.User;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	@Override
	@Transactional
	//전체 사용자 목록 불러오기 
	public List<User> getUserList(){
		return userDao.selectAll();
	}
	
	@Override
	@Transactional
	//사용자 등록하기 (회원가입)
	public boolean signup(User user) {
		try {
			userDao.insertOne(user);
			return true;
		} catch (Exception e) {
			//필요한 경우 예외를 로깅하거나 처리
			return false;
		}
		
	}
	
	@Override
	@Transactional
	//로그인 하기 
	public User login(String email, String password) {
		return userDao.selectOne(email, password);
	}


	
	
	

}
